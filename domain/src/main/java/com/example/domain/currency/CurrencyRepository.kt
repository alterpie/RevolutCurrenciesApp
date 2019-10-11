package com.example.domain.currency

import com.example.domain.currency.model.CurrenciesData

interface CurrencyRepository {
    suspend fun loadCurrencies(base:String): CurrenciesData
}
