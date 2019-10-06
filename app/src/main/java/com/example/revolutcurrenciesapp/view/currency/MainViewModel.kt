package com.example.revolutcurrenciesapp.view.currency

import com.example.domain.CurrencyDomain
import com.example.domain.LoadCurrenciesParams
import com.example.domain.LoadCurrenciesUseCase
import com.example.revolutcurrenciesapp.base.BaseViewModel
import com.example.revolutcurrenciesapp.common.viewmodel.ViewModelAction
import com.example.revolutcurrenciesapp.mapper.CurrencyModelMapper
import com.example.revolutcurrenciesapp.model.CurrencyModel
import com.example.revolutcurrenciesapp.util.subscribeEmpty
import io.reactivex.Observable
import java.util.concurrent.TimeUnit
import javax.inject.Inject

class MainViewModel @Inject constructor(
    private val loadCurrenciesUseCase: LoadCurrenciesUseCase,
    private val currencyModelMapper: CurrencyModelMapper
) : BaseViewModel() {

    val loadCurrenciesAction = ViewModelAction(loadCurrenciesUseCase, currencyModelMapper).apply {
        error = {
            started = false
            disposables.clear()
        }
    }

    private var started = false

    fun loadCurrencies(name: String, amount: Double) {
        if (!started) {
            started = true
            Observable.interval(REPEAT_PERIOD_SECONDS, TimeUnit.SECONDS)
                .map {
                    loadCurrenciesAction.execute(
                        LoadCurrenciesParams(CurrencyDomain.Currency(name, amount))
                    ).subscribeEmpty()
                        .autoDispose()
                }
                .subscribe()
                .autoDispose()
        }
    }

    fun setBaseCurrency(currencyModel: CurrencyModel) {
        val oldBaseCurrency = loadCurrenciesUseCase.getBaseCurrency()
        loadCurrenciesUseCase.updateBaseCurrency(
            CurrencyDomain.Currency(
                currencyModel.name,
                currencyModel.amount
            )
        )
        if ((loadCurrenciesAction.valueData ?: emptyList()).isNotEmpty())
            loadCurrenciesAction.postToLiveData(mutableListOf<CurrencyModel>().apply {
                addAll((loadCurrenciesAction.valueData ?: emptyList()))
                removeAll { it.name == oldBaseCurrency?.name || it.name == currencyModel.name }
                add(0, currencyModel)
            })
    }

    fun setBaseAmount(amount: Double) {
        loadCurrenciesAction.postToLiveData(
            currencyModelMapper.map(loadCurrenciesUseCase.updateAmounts(amount))
        )
    }

    fun retryLoad() {
        loadCurrenciesUseCase.getBaseCurrency()?.let {
            loadCurrencies(it.name, it.amount)
        }
    }

    companion object {
        private const val REPEAT_PERIOD_SECONDS = 1L
    }
}