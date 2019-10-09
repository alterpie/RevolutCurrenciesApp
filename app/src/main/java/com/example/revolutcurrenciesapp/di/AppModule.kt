package com.example.revolutcurrenciesapp.di

import android.content.Context
import android.net.ConnectivityManager
import com.example.revolutcurrenciesapp.features.currency.CurrencyViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    single { androidContext().getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager }
}
