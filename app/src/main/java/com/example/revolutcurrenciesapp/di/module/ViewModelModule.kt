package com.example.revolutcurrenciesapp.di.module

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.revolutcurrenciesapp.common.viewmodel.ViewModelFactory
import com.example.revolutcurrenciesapp.di.ViewModelKey
import com.example.revolutcurrenciesapp.view.currency.CurrencyViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
interface ViewModelModule {

    @Binds
    fun viewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory

    @Binds
    @IntoMap
    @ViewModelKey(CurrencyViewModel::class)
    fun currencyViewModel(currencyViewModel: CurrencyViewModel): ViewModel
}
