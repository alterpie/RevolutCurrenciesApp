package com.example.data

import com.example.data.error.ApiException
import io.reactivex.Single
import retrofit2.Call
import retrofit2.CallAdapter
import retrofit2.HttpException
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import java.io.IOException
import java.lang.reflect.Type

class RxErrorHandlingCallAdapterFactory : CallAdapter.Factory() {

    private val original: RxJava2CallAdapterFactory = RxJava2CallAdapterFactory.create()

    override fun get(
        returnType: Type,
        annotations: Array<Annotation>,
        retrofit: Retrofit
    ): CallAdapter<Any, Single<*>>? {
        return RxCallAdapterWrapper(retrofit, original.get(returnType, annotations, retrofit) as CallAdapter<Any, Any>)
    }

    open inner class RxCallAdapterWrapper<Any>(
        private val retrofit: Retrofit,
        private val wrapped: CallAdapter<Any, Any>?
    ) : CallAdapter<Any, Single<*>> {

        override fun responseType(): Type? {
            return wrapped?.responseType()
        }

        override fun adapt(call: Call<Any>): Single<*> {
            return (wrapped?.adapt(call) as Single<*>)
                .onErrorResumeNext { t: Throwable -> Single.error(asApiException(t)) }
        }

        private fun asApiException(throwable: Throwable): ApiException {
            if (throwable is HttpException) {
                val response = throwable.response()
                return ApiException.httpException(response, throwable, retrofit)
            }

            if (throwable is IOException) {
                return ApiException.networkException(throwable)
            }

            return ApiException.unexpectedException(throwable)
        }
    }
}