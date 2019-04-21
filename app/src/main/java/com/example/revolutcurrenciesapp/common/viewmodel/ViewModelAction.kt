package com.example.revolutcurrenciesapp.common.viewmodel

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.example.domain.Interactor
import com.example.domain.error.AppError
import com.example.revolutcurrenciesapp.mapper.BaseModelMapper
import io.reactivex.Single

class ViewModelAction<Params, Response, Model>(
    private val interactor: Interactor<Params, Response>,
    private val mapper: BaseModelMapper<Response, Model>
) {

    private val liveDataValue = MutableLiveData<Model>()
    private val liveDataError = MutableLiveData<AppError>()
    private val liveDataProgress = MutableLiveData<Boolean>()

    fun execute(params: Params): Single<Response> {
        liveDataProgress.postValue(true)
        liveDataError.postValue(null)

        return interactor.execute(params)
            .doOnSuccess {
                val model = mapper.map(it)
                liveDataValue.postValue(model)
            }
            .doOnError { liveDataError.postValue(interactor.errorMapper.transform(it)) }
            .doAfterTerminate { liveDataProgress.postValue(false) }
    }

    fun observe(
        owner: LifecycleOwner,
        doOnSuccess: (model: Model) -> Unit = {},
        doOnError: (error: AppError) -> Unit = {},
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

    fun getValueData() = liveDataValue.value
}