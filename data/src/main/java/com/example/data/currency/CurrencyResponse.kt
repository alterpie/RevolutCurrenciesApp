package com.example.data.currency

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class CurrencyResponse(
    @Json(name = "base") val base: String,
    @Json(name = "date") val date: String,
    @Json(name = "rates") val rates: Map<String, Double>
)
