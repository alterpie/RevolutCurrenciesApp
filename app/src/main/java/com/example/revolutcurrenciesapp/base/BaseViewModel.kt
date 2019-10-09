package com.example.revolutcurrenciesapp.base

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.revolutcurrenciesapp.features.ViewState
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.cancel

abstract class BaseViewModel<T : ViewState> : ViewModel() {

    init {
//        updateState(getInitialState())
    }

    val state: LiveData<T>
        get() = _state
    private val _state = MutableLiveData<T>()

    val events = EventQueue()

    protected val scope = CoroutineScope(SupervisorJob() + Dispatchers.Main.immediate)

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
        scope.cancel()
    }
}
