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
) :
    Interactor<LoadCurrenciesParams, CurrencyDomain>(errorMapper, workerScheduler) {
    override fun action(params: LoadCurrenciesParams): Single<CurrencyDomain> =
        currencyRepository.loadCurrencies(params.base)
}