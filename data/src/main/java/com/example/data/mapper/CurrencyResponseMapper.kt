package com.example.data.mapper

import com.example.data.currency.CurrencyResponse
import com.example.domain.currency.model.CurrenciesData
import com.example.domain.currency.model.Currency
import java.text.SimpleDateFormat
import java.util.*

class CurrencyResponseMapper : BaseDataMapper<CurrencyResponse, CurrenciesData>() {

    private val dateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())

    override fun map(response: CurrencyResponse): CurrenciesData = with(response) {
        CurrenciesData(
            base,
            parseDate(date),
            rates.map {
                Currency(
                    it.key,
                    it.value
                )
            })
    }

    private fun parseDate(date: String) = dateFormat.parse(date)
}