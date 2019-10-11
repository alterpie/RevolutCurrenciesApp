package com.example.revolutcurrenciesapp.view.currency

import com.example.revolutcurrenciesapp.base.ScreenState
import com.example.revolutcurrenciesapp.view.currency.model.CurrencyUi

data class CurrencyViewState(
    val currencies: List<CurrencyUi>,
    val screenState: ScreenState
) : ViewState
