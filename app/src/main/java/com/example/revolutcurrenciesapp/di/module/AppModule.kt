package com.example.revolutcurrenciesapp.di.module

import android.content.Context
import android.net.ConnectivityManager
import com.example.domain.currency.CurrencyInteractor
import com.example.domain.currency.CurrencyInteractorImpl
import com.example.domain.currency.CurrencyRepository
import com.example.revolutcurrenciesapp.AppDelegate
import dagger.Module
import dagger.Provides
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Module(
    includes = [
        AndroidSupportInjectionModule::class,
        ActivityModule::class,
        ViewModelModule::class,
        DataModule::class
    ]
)
class AppModule {

    @Provides
    @Singleton
    fun provideContext(app: AppDelegate): Context = app.applicationContext

    @Provides
    @Singleton
    fun connectivityManager(context: Context): ConnectivityManager {
        return context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    }

    @Provides
    fun currencyInteractor(currencyRepository: CurrencyRepository): CurrencyInteractor {
        return CurrencyInteractorImpl(currencyRepository)
    }
}
