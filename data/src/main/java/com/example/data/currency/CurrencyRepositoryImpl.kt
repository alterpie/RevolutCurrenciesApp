package com.example.data.currency

import com.example.data.RevolutApi
import com.example.data.mapper.CurrencyResponseMapper
import com.example.domain.CurrencyRepository
import com.example.domain.currency.model.CurrenciesData

class CurrencyRepositoryImpl(
    private val revolutApi: RevolutApi
) : CurrencyRepository {
    override suspend fun loadCurrencies(base: String): CurrenciesData {
        return revolutApi.loadCurrencies(base).let(CurrencyResponseMapper()::map)
    }
}
