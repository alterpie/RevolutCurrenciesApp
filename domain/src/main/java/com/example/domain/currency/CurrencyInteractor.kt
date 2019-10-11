package com.example.domain.currency

import com.example.domain.currency.model.Currency
import com.example.domain.currency.model.CurrencyData
import io.reactivex.Observable

interface CurrencyInteractor {
    fun observeCurrencies(): Observable<CurrencyData>
    fun updateBaseCurrency(newBase: Currency)
    fun updateBaseAmount(amount: Double)
}
