package com.example.revolutcurrenciesapp.di

import com.example.revolutcurrenciesapp.AppDelegate
import com.example.revolutcurrenciesapp.di.module.AppModule
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Component(modules = [AppModule::class])
@Singleton
interface AppComponent {
    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: AppDelegate): Builder

        fun build(): AppComponent
    }

    fun inject(app: AppDelegate)
}