package com.example.domain.currency

import com.example.domain.currency.model.CurrenciesData
import com.example.domain.currency.model.Currency
import com.example.domain.extinsions.intervalFlow
import kotlinx.coroutines.channels.ConflatedBroadcastChannel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.distinctUntilChanged

class CurrencyInteractorImpl(
    private val currencyRepository: CurrencyRepository
) : CurrencyInteractor {

    companion object {
        private const val REPEAT_PERIOD_MS = 1000L
    }

    private val initialCurrency = Currency("EUR", 100.0)
    private val baseCurrencyChannel = ConflatedBroadcastChannel(initialCurrency)

    override fun observeCurrencies(): Flow<CurrenciesData> {
        return combine(
            intervalFlow(REPEAT_PERIOD_MS),
            baseCurrencyChannel
                .asFlow()
                .distinctUntilChanged()
        ) { _, baseCurrency ->
            val currencyData = currencyRepository.loadCurrencies(baseCurrency.name)
            val convertedByBase = convertCurrencies(baseCurrency, currencyData.rates)
            currencyData.copy(rates = listOf(baseCurrency) + convertedByBase)
        }
    }

    private fun convertCurrencies(
        baseCurrency: Currency,
        currencies: List<Currency>
    ): List<Currency> {
        return currencies.map { it.copy(amount = it.amount * baseCurrency.amount) }
    }

    override fun updateBaseCurrency(newBase: Currency) {
        baseCurrencyChannel.offer(baseCurrencyChannel.value.copy(name = newBase.name))
    }

    override fun updateBaseAmount(amount: Double) {
        baseCurrencyChannel.offer(baseCurrencyChannel.value.copy(amount = amount))
    }
}
