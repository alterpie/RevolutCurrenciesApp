package com.example.domain

import io.reactivex.Scheduler
import io.reactivex.Single
import javax.inject.Inject

class LoadCurrenciesUseCase @Inject constructor(
    workerScheduler: Scheduler,
    private val currencyRepository: CurrencyRepository
) :
    Interactor<LoadCurrenciesParams, CurrencyDomain>(workerScheduler) {
    override fun action(params: LoadCurrenciesParams): Single<CurrencyDomain> =
        currencyRepository.loadCurrencies(params.base)
}