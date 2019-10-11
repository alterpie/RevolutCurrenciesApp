package com.example.domain.currency

import com.example.domain.currency.model.CurrencyData
import io.reactivex.Single

interface CurrencyRepository {
    fun loadCurrencies(base:String): Single<CurrencyData>
}
