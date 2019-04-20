package com.example.domain

import java.util.*

data class CurrencyDomain(
    val base: String,
    val date: Date,
    val rates: List<Currency>
) {
    data class Currency(
        val name: String,
        val amount: Double
    )
}