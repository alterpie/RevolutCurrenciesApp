package com.example.data.mapper

import com.example.data.currency.CurrencyResponse
import com.example.domain.currency.model.Currency
import com.example.domain.currency.model.CurrencyData
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject

class CurrencyResponseMapper @Inject constructor() :
    BaseDataMapper<CurrencyResponse, CurrencyData>() {

    private val dateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())

    override fun map(response: CurrencyResponse): CurrencyData = with(response) {
        CurrencyData(
            base,
            parseDate(date),
            rates.map { Currency(it.key, it.value) })
    }

    private fun parseDate(date: String) = dateFormat.parse(date)
}
