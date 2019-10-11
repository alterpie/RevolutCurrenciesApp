package com.example.revolutcurrenciesapp.view.currency.model

import com.example.revolutcurrenciesapp.base.adapter.AdapterKeys
import com.example.revolutcurrenciesapp.base.adapter.ViewType

data class CurrencyUi(
    val name: String,
    val amount: Double,
    val currencyDetails: CurrencyDetails
) : ViewType {
    override fun getViewType(): Int = AdapterKeys.CURRENCY_ITEM
}
