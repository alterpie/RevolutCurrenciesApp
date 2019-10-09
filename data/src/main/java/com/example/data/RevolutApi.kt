package com.example.data

import com.example.data.currency.CurrencyResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface RevolutApi {

    @GET("/latest")
    suspend fun loadCurrencies(@Query("base") base: String): CurrencyResponse
}
