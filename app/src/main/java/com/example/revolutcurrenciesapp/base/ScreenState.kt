package com.example.revolutcurrenciesapp.base

sealed class ScreenState {
    object Loading : ScreenState()
    object Content : ScreenState()
    data class Error(val error: Throwable) : ScreenState()
}
