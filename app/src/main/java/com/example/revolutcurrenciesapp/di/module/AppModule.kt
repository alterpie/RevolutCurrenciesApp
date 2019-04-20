package com.example.revolutcurrenciesapp.di.module

import android.content.Context
import com.example.revolutcurrenciesapp.AppDelegate
import dagger.Module
import dagger.Provides
import dagger.android.support.AndroidSupportInjectionModule
import io.reactivex.Scheduler
import io.reactivex.schedulers.Schedulers
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
    fun provideContext(app: AppDelegate): Context {
        return app.applicationContext
    }

    @Provides
    @Singleton
    fun provideWorkerScheduler() : Scheduler = Schedulers.io()
}