package com.example.revolutcurrenciesapp.mapper

import com.example.domain.CurrencyDomain
import com.example.revolutcurrenciesapp.R
import com.example.revolutcurrenciesapp.model.CurrencyModel
import javax.inject.Inject

class CurrencyModelMapper @Inject constructor() : BaseModelMapper<List<CurrencyDomain.Currency>, List<CurrencyModel>>() {
    override fun map(response: List<CurrencyDomain.Currency>): List<CurrencyModel> = response.map {
        CurrencyModel(it.name, it.amount, mapCurrencyDetails(it.name), mapCurrencyFlag(it.name))
    }

    private fun mapCurrencyDetails(currencyName: String) = when (currencyName) {
        "EUR" -> R.string.euro
        "THB" -> R.string.thai_bahl
        "JPY" -> R.string.japanese_yen
        "DKK" -> R.string.danish_krone
        "CZK" -> R.string.czech_koruna
        "USD" -> R.string.us_dollar
        "AUD" -> R.string.australian_dollar
        "BGN" -> R.string.bulgarian_lev
        "BRL" -> R.string.brazil_real
        "CAD" -> R.string.canada_dollar
        "CNY" -> R.string.china_yuan
        "CHF" -> R.string.switzerland_frank
        "GBP" -> R.string.british_pound
        "HKD" -> R.string.hong_kong_dollar
        "HRK" -> R.string.croatia_kuna
        "HUF" -> R.string.hungarian_forint
        "IDR" -> R.string.indonesian_rupiah
        "ILS" -> R.string.israeli_shekel
        "INR" -> R.string.india_rupee
        "ISK" -> R.string.icelandic_krona
        "KRW" -> R.string.korea_south_won
        "MXN" -> R.string.mexican_peso
        "MYR" -> R.string.malaysian_ringgit
        "NOK" -> R.string.norway_krone
        "NZD" -> R.string.new_zealand_dollar
        "PHP" -> R.string.philippines_peso
        "PLN" -> R.string.poland_zloty
        "RON" -> R.string.romanian_leu
        "RUB" -> R.string.russian_ruble
        "SEK" -> R.string.sweden_krona
        "TRY" -> R.string.turkish_lire
        "SGD" -> R.string.singapore_dollar
        "ZAR" -> R.string.south_africa_rand
        else -> R.string.empty_string
    }

    private fun mapCurrencyFlag(currencyName: String) = when(currencyName){
        "EUR" -> R.drawable.ic_european_union
        "THB" -> R.drawable.ic_thailand
        "JPY" -> R.drawable.ic_japan
        "DKK" -> R.drawable.ic_denmark
        "CZK" -> R.drawable.ic_czech_republic
        "USD" -> R.drawable.ic_united_states
        "AUD" -> R.drawable.ic_australia
        "BGN" -> R.drawable.ic_bulgaria
        "BRL" -> R.drawable.ic_brazil
        "CAD" -> R.drawable.ic_canada
        "CNY" -> R.drawable.ic_china
        "CHF" -> R.drawable.ic_switzerland
        "GBP" -> R.drawable.ic_united_kingdom
        "HKD" -> R.drawable.ic_hong_kong
        "HRK" -> R.drawable.ic_croatia
        "HUF" -> R.drawable.ic_hungary
        "IDR" -> R.drawable.ic_indonesia
        "ILS" -> R.drawable.ic_israel
        "INR" -> R.drawable.ic_india
        "ISK" -> R.drawable.ic_iceland
        "KRW" -> R.drawable.ic_south_korea
        "MXN" -> R.drawable.ic_mexico
        "MYR" -> R.drawable.ic_malaysia
        "NOK" -> R.drawable.ic_norway
        "NZD" -> R.drawable.ic_new_zealand
        "PHP" -> R.drawable.ic_philippines
        "PLN" -> R.drawable.ic_poland
        "RON" -> R.drawable.ic_romania
        "RUB" -> R.drawable.ic_russia
        "SEK" -> R.drawable.ic_sweden
        "TRY" -> R.drawable.ic_turkey
        "SGD" -> R.drawable.ic_singapore
        "ZAR" -> R.drawable.ic_south_africa
        else -> 0
    }
}