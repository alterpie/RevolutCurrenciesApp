package com.example.revolutcurrenciesapp.di.module

import com.example.revolutcurrenciesapp.di.ActivityScope
import com.example.revolutcurrenciesapp.view.currency.CurrencyActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityModule {

    @ActivityScope
    @ContributesAndroidInjector
    abstract fun mainActivityInjector(): CurrencyActivity
}
