package com.example.revolutcurrenciesapp.features.currency.di

import com.example.data.currency.CurrencyRepositoryImpl
import com.example.domain.currency.CurrencyRepository
import com.example.domain.currency.CurrencyInteractor
import com.example.domain.currency.CurrencyInteractorImpl
import com.example.revolutcurrenciesapp.features.currency.CurrencyViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val currencyModule = module {
    viewModel { CurrencyViewModel(get()) }
    single { CurrencyRepositoryImpl(get()) as CurrencyRepository }
    single { CurrencyInteractorImpl(get()) as CurrencyInteractor }
}
