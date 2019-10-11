package com.example.revolutcurrenciesapp.base

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.revolutcurrenciesapp.view.currency.ViewState
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

abstract class BaseViewModel<T : ViewState> : ViewModel() {

    val state: LiveData<T>
        get() = _state
    private val _state = MutableLiveData<T>()

    init {
        _state.value = this.getInitialState()
    }

    val events = EventQueue()

    protected val disposables = CompositeDisposable()

    protected fun Disposable.autoDispose(): Disposable {
        disposables.add(this)
        return this
    }

    protected val stateValue: T
        get() = state.value!!

    abstract fun getInitialState(): T

    protected fun updateState(value: T) {
        _state.value = value
    }

    protected fun updateState(body: T.() -> T): T {
        val newValue = body(stateValue)
        _state.value = newValue
        return newValue
    }

    protected fun handleError(throwable: Throwable) {
        events.offer(ErrorEvent(throwable))
    }

    override fun onCleared() {
        super.onCleared()
        disposables.clear()
    }
}

