package com.example.data.mapper

import com.example.data.currency.CurrencyResponse
import com.example.domain.CurrencyDomain
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject

class CurrencyResponseMapper @Inject constructor() :
    BaseDataMapper<CurrencyResponse, CurrencyDomain>() {

    private val dateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())

    override fun map(response: CurrencyResponse): com.example.domain.CurrencyDomain = with(response) {
        CurrencyDomain(base, parseDate(date), rates.map { CurrencyDomain.Currency(it.key, it.value) })
    }

    private fun parseDate(date: String) = dateFormat.parse(date)
}