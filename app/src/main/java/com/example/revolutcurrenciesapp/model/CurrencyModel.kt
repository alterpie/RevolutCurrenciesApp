package com.example.revolutcurrenciesapp.model

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.example.revolutcurrenciesapp.common.adapter.AdapterKeys
import com.example.revolutcurrenciesapp.common.adapter.ViewType

data class CurrencyModel(
    val name: String,
    val amount: Double,
    @StringRes val currencyDetails: Int,
    @DrawableRes val countryFlagDrawable: Int
) : ViewType {
    override fun getViewType(): Int = AdapterKeys.CURRENCY_ITEM
}