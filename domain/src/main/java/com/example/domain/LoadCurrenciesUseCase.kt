package com.example.domain

import com.example.domain.error.AppError
import com.example.domain.error.ErrorMapper
import io.reactivex.Scheduler
import io.reactivex.Single
import javax.inject.Inject

class LoadCurrenciesUseCase @Inject constructor(
    errorMapper: ErrorMapper<AppError>,
    workerScheduler: Scheduler,
    private val currencyRepository: CurrencyRepository
) : Interactor<LoadCurrenciesParams, List<CurrencyDomain.Currency>>(errorMapper, workerScheduler) {

    private var notConvertedCurrencies = emptyList<CurrencyDomain.Currency>()
    private var baseCurrency: CurrencyDomain.Currency? = null

    override fun action(params: LoadCurrenciesParams): Single<List<CurrencyDomain.Currency>> {
        if (baseCurrency == null) baseCurrency = params.base
        return currencyRepository.loadCurrencies(baseCurrency!!.name).map {
            notConvertedCurrencies = it.rates
            convertCurrencies(baseCurrency!!, it.rates)
        }
    }

    private fun convertCurrencies(
        baseCurrency: CurrencyDomain.Currency,
        currencies: List<CurrencyDomain.Currency>
    ): List<CurrencyDomain.Currency> =
        currencies.map { it.copy(amount = it.amount * baseCurrency.amount) }.toMutableList().apply {
            add(0, baseCurrency)
        }

    fun updateAmounts(amount: Double): List<CurrencyDomain.Currency> {
        if (baseCurrency?.amount != amount) baseCurrency = baseCurrency!!.copy(amount = amount)
        return convertCurrencies(baseCurrency!!, notConvertedCurrencies)
    }

    fun updateBaseCurrency(newBase: CurrencyDomain.Currency) {
        baseCurrency = if (baseCurrency == null) newBase
        else baseCurrency!!.copy(name = newBase.name)
    }

    fun getBaseCurrency() = baseCurrency
}