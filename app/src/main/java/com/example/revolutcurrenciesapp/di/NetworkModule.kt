package com.example.revolutcurrenciesapp.di

import android.net.ConnectivityManager
import com.example.data.NetworkErrorInterceptor
import com.example.data.RevolutApi
import com.example.revolutcurrenciesapp.BuildConfig
import com.squareup.moshi.Moshi
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit

private const val timeout = 30L

val networkModule = module {
    single { Moshi.Builder().build() }
    single { getCommonOkHttpBuilder(get())
        .build() }
    single {
        getCommonRetrofitBuilder(get())
            .client(get())
            .build()
    }
    single { get<Retrofit>().create(RevolutApi::class.java) }
}

private fun getCommonRetrofitBuilder(moshi: Moshi): Retrofit.Builder {
    return Retrofit.Builder()
        .baseUrl(com.example.data.BuildConfig.API_APP_URL)
        .addConverterFactory(MoshiConverterFactory.create(moshi))
}

private fun getCommonOkHttpBuilder(connectivityManager: ConnectivityManager): OkHttpClient.Builder {
    val okHttpClientBuilder = OkHttpClient.Builder()
        .readTimeout(timeout, TimeUnit.SECONDS)
        .writeTimeout(timeout, TimeUnit.SECONDS)
        .connectTimeout(timeout, TimeUnit.SECONDS)
        .addInterceptor(NetworkErrorInterceptor(connectivityManager))

    if (BuildConfig.DEBUG) {
        okHttpClientBuilder.addNetworkInterceptor(HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        })
    }

    return okHttpClientBuilder
}
