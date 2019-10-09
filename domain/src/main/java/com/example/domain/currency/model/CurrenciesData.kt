package com.example.domain.currency.model

import java.util.*

data class CurrenciesData(
    val base: String,
    val date: Date,
    val rates: List<Currency>
)
