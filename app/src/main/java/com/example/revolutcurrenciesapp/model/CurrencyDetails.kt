package com.example.revolutcurrenciesapp.model

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

data class CurrencyDetails(
    @StringRes val name: Int,
    @DrawableRes val icon: Int
)
