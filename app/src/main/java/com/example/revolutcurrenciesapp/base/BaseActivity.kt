package com.example.revolutcurrenciesapp.base

import android.os.Bundle
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import org.koin.core.context.loadKoinModules
import org.koin.core.context.unloadKoinModules
import org.koin.core.module.Module

abstract class BaseActivity : AppCompatActivity() {

    @LayoutRes
    abstract fun getLayoutId(): Int

    abstract val featureModule: Module

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(getLayoutId())
        loadKoinModules(featureModule)
    }

    override fun onDestroy() {
        super.onDestroy()
        unloadKoinModules(featureModule)
    }

    open fun handleEvent(event: Event){
        // base error handling
    }
}
