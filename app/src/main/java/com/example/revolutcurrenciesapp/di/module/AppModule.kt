package com.example.revolutcurrenciesapp.di.module

import android.content.Context
import com.example.data.error.ApiErrorMapper
import com.example.domain.error.AppError
import com.example.domain.error.ErrorMapper
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
    fun provideContext(app: AppDelegate): Context = app.applicationContext

    @Provides
    @Singleton
    fun provideWorkerScheduler() : Scheduler = Schedulers.io()

    @Provides
    fun provideErrorMapper() : ErrorMapper<AppError> = ApiErrorMapper()
}