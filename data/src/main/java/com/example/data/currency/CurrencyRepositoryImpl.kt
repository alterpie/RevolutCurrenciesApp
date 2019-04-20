package com.example.data.currency

import com.example.data.RevolutApi
import com.example.data.mapper.CurrencyResponseMapper
import com.example.domain.CurrencyDomain
import com.example.domain.CurrencyRepository
import io.reactivex.Single
import javax.inject.Inject

class CurrencyRepositoryImpl @Inject constructor(
    private val revolutApi: RevolutApi,
    private val currencyResponseMapper: CurrencyResponseMapper
) : CurrencyRepository {
    override fun loadCurrencies(base: String): Single<CurrencyDomain> =
        revolutApi.loadCurrencies(base).map { currencyResponseMapper.map(it) }
}