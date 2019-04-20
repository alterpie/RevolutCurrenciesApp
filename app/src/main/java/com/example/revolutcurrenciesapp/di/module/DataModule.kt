package com.example.revolutcurrenciesapp.di.module

import com.example.data.currency.CurrencyRepositoryImpl
import com.example.domain.CurrencyRepository
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module(includes = [RemoteModule::class])
class DataModule {

    @Provides
    @Singleton
    fun provideCurrencyRepository(currencyRepositoryImpl: CurrencyRepositoryImpl): CurrencyRepository =
        currencyRepositoryImpl
}