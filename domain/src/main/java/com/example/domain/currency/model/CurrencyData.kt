package com.example.domain.currency.model

import java.util.*

data class CurrencyData(
    val base: String,
    val date: Date,
    val rates: List<Currency>
)
