package com.example.revolutcurrenciesapp.model

import com.example.revolutcurrenciesapp.common.adapter.AdapterKeys
import com.example.revolutcurrenciesapp.common.adapter.ViewType

data class CurrencyModel(
    val name: String,
    val amount: Double,
    val currencyDetails: CurrencyDetails
) : ViewType {
    override fun getViewType(): Int = AdapterKeys.CURRENCY_ITEM
}