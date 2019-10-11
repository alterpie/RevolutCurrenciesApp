package com.example.data

import com.example.data.currency.CurrencyResponse
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface RevolutApi {

    @GET("/latest")
    fun loadCurrencies(@Query("base") base: String): Single<CurrencyResponse>
}
