package com.example.revolutcurrenciesapp.view.currency

import com.example.domain.currency.CurrencyInteractor
import com.example.domain.currency.model.Currency
import com.example.domain.currency.model.CurrencyData
import com.example.revolutcurrenciesapp.base.BaseViewModel
import com.example.revolutcurrenciesapp.base.ScreenState
import com.example.revolutcurrenciesapp.mapper.CurrencyModelMapper
import com.example.revolutcurrenciesapp.view.currency.model.CurrencyUi
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.rxkotlin.subscribeBy
import javax.inject.Inject

class CurrencyViewModel @Inject constructor(
    private val currencyInteractor: CurrencyInteractor
) : BaseViewModel<CurrencyViewState>() {

    init {
        loadCurrencies()
    }

    override fun getInitialState(): CurrencyViewState {
        return CurrencyViewState(emptyList(), ScreenState.Loading)
    }

    private fun loadCurrencies() {
        currencyInteractor.observeCurrencies()
            .map(::mapToUiModel)
            .map(::mapToState)
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe { updateState { copy(screenState = ScreenState.Loading) } }
            .subscribeBy(
                onNext = ::updateState,
                onError = {
                    handleError(it)
                    updateState { copy(screenState = ScreenState.Error(it)) }
                }
            )
            .autoDispose()
    }

    fun setBaseCurrency(currencyModel: CurrencyUi) {
        currencyInteractor.updateBaseCurrency(Currency(currencyModel.name, currencyModel.amount))
    }

    fun setBaseAmount(amount: Double) {
        currencyInteractor.updateBaseAmount(amount)
    }

    fun retryLoad() {
        loadCurrencies()
    }

    private fun mapToUiModel(currenciesData: CurrencyData): List<CurrencyUi> {
        return CurrencyModelMapper().map(currenciesData.rates)
    }

    private fun mapToState(currencies: List<CurrencyUi>): CurrencyViewState {
        return CurrencyViewState(currencies, ScreenState.Content)
    }
}
