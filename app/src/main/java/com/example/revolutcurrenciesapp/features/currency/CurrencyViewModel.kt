package com.example.revolutcurrenciesapp.features.currency

import com.example.domain.currency.CurrencyInteractor
import com.example.domain.currency.model.CurrenciesData
import com.example.domain.currency.model.Currency
import com.example.revolutcurrenciesapp.base.BaseViewModel
import com.example.revolutcurrenciesapp.base.ScreenState
import com.example.revolutcurrenciesapp.features.currency.model.CurrencyUi
import com.example.revolutcurrenciesapp.mapper.CurrencyModelMapper
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class CurrencyViewModel(
    private val currencyInteractor: CurrencyInteractor
) : BaseViewModel<CurrencyViewState>() {

    init {
        loadCurrencies()
    }

    override fun getInitialState(): CurrencyViewState {
        return CurrencyViewState(emptyList(), ScreenState.Loading)
    }

    fun loadCurrencies() {
        val exceptionHandler = CoroutineExceptionHandler { _, throwable ->

        }
        scope.launch(exceptionHandler) {
            currencyInteractor.observeCurrencies()
                .map { mapToUiModel(it) }
                .flowOn(Dispatchers.IO)
                .onEach {
                    updateState(CurrencyViewState(it, ScreenState.Content))
                }
                .onStart { updateState(CurrencyViewState(emptyList(), ScreenState.Loading)) }
                .catch {
                    handleError(it)
                    updateState { copy(screenState = ScreenState.Error(it)) }
                }
                .launchIn(this)
        }
    }

    private fun mapToUiModel(currenciesData: CurrenciesData): List<CurrencyUi> {
        return CurrencyModelMapper().map(currenciesData.rates)
    }

    fun changeBaseCurrency(currencyUi: CurrencyUi) {
        currencyInteractor.updateBaseCurrency(
            Currency(
                currencyUi.name,
                currencyUi.amount
            )
        )
    }

    fun setBaseAmount(amount: Double) {
        currencyInteractor.updateBaseAmount(amount)
    }

    fun retry() {
        loadCurrencies()
    }
}
