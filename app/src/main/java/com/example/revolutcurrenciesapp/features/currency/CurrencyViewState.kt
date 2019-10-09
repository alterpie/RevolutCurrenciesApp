package com.example.revolutcurrenciesapp.features.currency

import com.example.revolutcurrenciesapp.base.ScreenState
import com.example.revolutcurrenciesapp.features.ViewState
import com.example.revolutcurrenciesapp.features.currency.model.CurrencyUi

data class CurrencyViewState(
    val currencies: List<CurrencyUi>,
    val screenState: ScreenState
) : ViewState
