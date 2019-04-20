package com.example.domain

import io.reactivex.Single

interface CurrencyRepository {
    fun loadCurrencies(base:String): Single<CurrencyDomain>
}