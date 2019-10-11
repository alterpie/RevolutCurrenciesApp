package com.example.data.currency

import com.example.data.RevolutApi
import com.example.data.mapper.CurrencyResponseMapper
import com.example.domain.currency.CurrencyRepository
import com.example.domain.currency.model.CurrencyData
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class CurrencyRepositoryImpl @Inject constructor(
    private val revolutApi: RevolutApi,
    private val currencyResponseMapper: CurrencyResponseMapper
) : CurrencyRepository {
    override fun loadCurrencies(base: String): Single<CurrencyData> =
        revolutApi.loadCurrencies(base).map { currencyResponseMapper.map(it) }
            .subscribeOn(Schedulers.io())
}
