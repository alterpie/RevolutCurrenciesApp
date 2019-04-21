package com.example.domain

import com.example.domain.error.AppError
import com.example.domain.error.ErrorMapper
import io.reactivex.Scheduler
import io.reactivex.Single

abstract class Interactor<Params, Response>(
    val errorMapper: ErrorMapper<AppError>,
    private val workerScheduler: Scheduler
) {

    abstract fun action(params: Params): Single<Response>
    fun execute(params: Params): Single<Response> = action(params).subscribeOn(workerScheduler)
}