package com.example.revolutcurrenciesapp

import android.app.Activity
import android.app.Application
import com.example.revolutcurrenciesapp.di.DaggerAppComponent
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector
import javax.inject.Inject

class AppDelegate : Application(), HasActivityInjector {
    @Inject
    lateinit var dispatchingAndroidInjector: DispatchingAndroidInjector<Activity>

    override fun onCreate() {
        super.onCreate()

        DaggerAppComponent
            .builder()
            .application(this)
            .build()
            .inject(this)
    }
    override fun activityInjector(): AndroidInjector<Activity> = dispatchingAndroidInjector
}
