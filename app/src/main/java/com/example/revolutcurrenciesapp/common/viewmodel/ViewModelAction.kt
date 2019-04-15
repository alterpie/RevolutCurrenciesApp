package com.example.revolutcurrenciesapp.common.viewmodel

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer

class ViewModelAction<Params, Response, Model> {

    private val liveDataValue = MutableLiveData<Model>()
    private val liveDataError = MutableLiveData<Throwable>()
    private val liveDataProgress = MutableLiveData<Boolean>()

    private var isExecuting = false

    fun execute(params: Params) {

        if (isExecuting) return

        liveDataProgress.postValue(true)
        liveDataError.postValue(null)

        // todo execute logic
    }

    fun observe(
        owner: LifecycleOwner,
        doOnSuccess: (model: Model) -> Unit = {},
        doOnError: (error: Throwable) -> Unit = {},
        doOnToggleProgress: (toggle: Boolean) -> Unit = {}
    ) {
        liveDataValue.observe(owner, Observer {
            if (it != null) doOnSuccess(it)
        })
        liveDataError.observe(owner, Observer {
            if (it != null) doOnError(it)
        })
        liveDataProgress.observe(owner, Observer {
            if (it != null) doOnToggleProgress(it)
        })
    }
}