package com.example.revolutcurrenciesapp.view.currency

import com.example.domain.LoadCurrenciesParams
import com.example.domain.LoadCurrenciesUseCase
import com.example.revolutcurrenciesapp.base.BaseViewModel
import com.example.revolutcurrenciesapp.common.viewmodel.ViewModelAction
import com.example.revolutcurrenciesapp.mapper.CurrencyModelMapper
import com.example.revolutcurrenciesapp.util.plusAssign
import com.example.revolutcurrenciesapp.util.subscribeEmpty
import javax.inject.Inject

class MainViewModel @Inject constructor(
    loadCurrenciesUseCase: LoadCurrenciesUseCase,
    currencyModelMapper: CurrencyModelMapper
) : BaseViewModel() {

    val loadCurrenciesAction = ViewModelAction(loadCurrenciesUseCase, currencyModelMapper)

    private var baseCurrency: String = "EUR"

    fun loadCurrencies() {
        disposables += loadCurrenciesAction.execute(LoadCurrenciesParams(baseCurrency))
            .subscribeEmpty()
    }

    fun setBaseCurrency(currency: String) {
        baseCurrency = currency
    }

}