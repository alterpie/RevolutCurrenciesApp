package com.example.revolutcurrenciesapp.util

import io.reactivex.Single
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.functions.BiConsumer

class EmptyBiConsumer<T, E: Throwable?> : BiConsumer<T, E> {
    override fun accept(t1: T?, t2: E?) {}
}

fun <T> Single<T>.subscribeEmpty() = subscribe(EmptyBiConsumer())
