package com.example.revolutcurrenciesapp.features.currency.model

import com.example.revolutcurrenciesapp.common.adapter.AdapterKeys
import com.example.revolutcurrenciesapp.common.adapter.ViewType

data class CurrencyUi(
    val name: String,
    val amount: Double,
    val currencyDetails: CurrencyDetails
) : ViewType {
    override fun getViewType(): Int = AdapterKeys.CURRENCY_ITEM
}
