package com.example.revolutcurrenciesapp.di.module

import android.net.ConnectivityManager
import com.example.data.NetworkErrorInterceptor
import com.example.data.RevolutApi
import com.example.revolutcurrenciesapp.BuildConfig
import com.squareup.moshi.Moshi
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
class RemoteModule {
    private val timeout = 30L

    @Provides
    @Singleton
    fun provideMoshi(): Moshi {
        return Moshi.Builder()
            .build()
    }

    private fun getCommonOkHttpBuilder(): OkHttpClient.Builder {
        val okHttpClientBuilder = OkHttpClient.Builder()
            .readTimeout(timeout, TimeUnit.SECONDS)
            .writeTimeout(timeout, TimeUnit.SECONDS)
            .connectTimeout(timeout, TimeUnit.SECONDS)

        if (BuildConfig.DEBUG) {
            okHttpClientBuilder.addNetworkInterceptor(HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.BODY
            })
        }

        return okHttpClientBuilder
    }

    private fun getCommonRetrofitBuilder(moshi: Moshi): Retrofit.Builder {
        return Retrofit.Builder()
            .baseUrl(com.example.data.BuildConfig.API_APP_URL)
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
    }

    @Provides
    @Singleton
    fun provideOkHttpClient(connectivityManager: ConnectivityManager): OkHttpClient =
        getCommonOkHttpBuilder()
            .addNetworkInterceptor(NetworkErrorInterceptor(connectivityManager))
            .build()

    @Provides
    @Singleton
    fun provideRetrofit(moshi: Moshi, okHttpClient: OkHttpClient): Retrofit =
        getCommonRetrofitBuilder(moshi)
            .client(okHttpClient)
            .build()

    @Provides
    @Singleton
    fun provideRemoteService(retrofit: Retrofit): RevolutApi =
        retrofit.create(RevolutApi::class.java)
}
