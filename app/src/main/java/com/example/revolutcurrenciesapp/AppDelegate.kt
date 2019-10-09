package com.example.revolutcurrenciesapp

import android.app.Application
import com.example.revolutcurrenciesapp.di.appModule
import com.example.revolutcurrenciesapp.di.networkModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class AppDelegate : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger()
            androidContext(this@AppDelegate)
            modules(listOf(appModule, networkModule))
        }
    }
}
