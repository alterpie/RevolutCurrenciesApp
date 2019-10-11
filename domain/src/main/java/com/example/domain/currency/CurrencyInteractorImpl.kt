package com.example.domain.currency

import com.example.domain.currency.model.Currency
import com.example.domain.currency.model.CurrencyData
import io.reactivex.Observable
import io.reactivex.rxkotlin.Observables
import io.reactivex.subjects.BehaviorSubject
import java.util.concurrent.TimeUnit
import javax.inject.Inject

class CurrencyInteractorImpl @Inject constructor(
    private val currencyRepository: CurrencyRepository
) : CurrencyInteractor {

    companion object {
        private const val REPEAT_PERIOD_MS = 1000L
    }

    private var initialCurrency: Currency = Currency("EUR", 100.0)
    private val baseCurrencyUpdates = BehaviorSubject.createDefault(initialCurrency)

    override fun observeCurrencies(): Observable<CurrencyData> {
        return Observables.combineLatest(
            Observable.interval(REPEAT_PERIOD_MS, TimeUnit.MILLISECONDS),
            baseCurrencyUpdates.distinctUntilChanged()
        )
            .switchMap { (_, baseCurrency) ->
                currencyRepository.loadCurrencies(baseCurrency.name)
                    .map { currencyData ->
                        val convertedByBase = convertCurrencies(baseCurrency, currencyData.rates)
                        currencyData.copy(rates = listOf(baseCurrency) + convertedByBase)
                    }
                    .toObservable()
            }
    }

    override fun updateBaseCurrency(newBase: Currency) {
        baseCurrencyUpdates.onNext(baseCurrencyUpdates.value!!.copy(name = newBase.name))
    }

    override fun updateBaseAmount(amount: Double) {
        baseCurrencyUpdates.onNext(baseCurrencyUpdates.value!!.copy(amount = amount))
    }

    private fun convertCurrencies(
        baseCurrency: Currency,
        currencies: List<Currency>
    ): List<Currency> =
        currencies.map { it.copy(amount = it.amount * baseCurrency.amount) }
}
