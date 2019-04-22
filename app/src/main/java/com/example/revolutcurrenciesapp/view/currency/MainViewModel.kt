package com.example.revolutcurrenciesapp.view.currency

import com.example.domain.LoadCurrenciesParams
import com.example.domain.LoadCurrenciesUseCase
import com.example.revolutcurrenciesapp.base.BaseViewModel
import com.example.revolutcurrenciesapp.common.viewmodel.ViewModelAction
import com.example.revolutcurrenciesapp.mapper.CurrencyModelMapper
import com.example.revolutcurrenciesapp.model.CurrencyModel
import com.example.revolutcurrenciesapp.util.plusAssign
import com.example.revolutcurrenciesapp.util.subscribeEmpty
import io.reactivex.Observable
import java.util.concurrent.TimeUnit
import javax.inject.Inject

class MainViewModel @Inject constructor(
    loadCurrenciesUseCase: LoadCurrenciesUseCase,
    currencyModelMapper: CurrencyModelMapper
) : BaseViewModel() {

    val loadCurrenciesAction = ViewModelAction(loadCurrenciesUseCase, currencyModelMapper).apply {
        mutateData = {
            notConvertedCurrencies = it.toMutableList().apply { add(0, baseCurrency!!) }
            convertCurrencies(it) }
        error = {
            started = false
            disposables.clear()
        }
    }

    private var notConvertedCurrencies = emptyList<CurrencyModel>()
    private var baseCurrency: CurrencyModel? = null
    private var started = false

    fun loadCurrencies() {
        if (!started) {
            started = true
            disposables += Observable.interval(1, TimeUnit.SECONDS)
                .map {
                    disposables += loadCurrenciesAction.execute(LoadCurrenciesParams(baseCurrency!!.name))
                        .subscribeEmpty()
                }
                .subscribe()
        }
    }

    fun setBaseCurrency(currencyModel: CurrencyModel) {
        val oldBaseCurrency = baseCurrency
        baseCurrency = currencyModel
        if ((loadCurrenciesAction.getValueData() ?: emptyList()).isNotEmpty())
            loadCurrenciesAction.postToLiveData(mutableListOf<CurrencyModel>().apply {
                addAll((loadCurrenciesAction.getValueData() ?: emptyList()))
                removeAll { it.name == oldBaseCurrency?.name || it.name == currencyModel.name }
                add(0, currencyModel)
            })
    }

    fun setBaseAmount(amount: Double) {
        if (baseCurrency?.amount != amount) {
            baseCurrency = baseCurrency!!.copy(amount = amount)
            loadCurrenciesAction.postToLiveData(
                (notConvertedCurrencies).let {
                    if (it.size > 1)
                        convertCurrencies(
                            it.subList(1, it.size)
                        ) else it
                }
            )
        }
    }

    fun isBaseCurrencySet() = baseCurrency != null

    private fun convertCurrencies(currencies: List<CurrencyModel>): List<CurrencyModel> =
        currencies.map { it.copy(amount = it.amount * baseCurrency!!.amount) }.toMutableList().apply {
            add(0, baseCurrency!!)
        }
}