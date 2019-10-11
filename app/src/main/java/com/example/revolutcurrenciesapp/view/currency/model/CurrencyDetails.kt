package com.example.revolutcurrenciesapp.view.currency.model

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

data class CurrencyDetails(
    @StringRes val name: Int,
    @DrawableRes val icon: Int
)
