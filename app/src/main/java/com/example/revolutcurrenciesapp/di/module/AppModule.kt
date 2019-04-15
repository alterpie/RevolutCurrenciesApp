package com.example.revolutcurrenciesapp.di.module

import android.content.Context
import com.example.revolutcurrenciesapp.AppDelegate
import dagger.Module
import dagger.Provides
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Module(
    includes = [
        AndroidSupportInjectionModule::class,
        ActivityModule::class,
        ViewModelModule::class
    ]
)
class AppModule {

    @Provides
    @Singleton
    fun provideContext(app: AppDelegate): Context {
        return app.applicationContext
    }
}