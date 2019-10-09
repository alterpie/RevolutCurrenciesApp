package com.example.domain.currency

import com.example.domain.currency.model.CurrenciesData
import com.example.domain.currency.model.Currency
import kotlinx.coroutines.flow.Flow

interface CurrencyInteractor {
    fun observeCurrencies(): Flow<CurrenciesData>
    fun updateBaseCurrency(newBase: Currency)
    fun updateBaseAmount(amount: Double)
}
