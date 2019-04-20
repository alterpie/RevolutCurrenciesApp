package com.example.domain

import io.reactivex.Scheduler
import io.reactivex.Single

abstract class Interactor<Params, Response>(private val workerScheduler: Scheduler) {

    abstract fun action(params: Params): Single<Response>
    fun execute(params: Params): Single<Response> = action(params).subscribeOn(workerScheduler)
}