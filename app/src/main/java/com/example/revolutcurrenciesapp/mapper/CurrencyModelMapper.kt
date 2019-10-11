package com.example.revolutcurrenciesapp.mapper

import com.example.domain.currency.model.Currency
import com.example.revolutcurrenciesapp.R
import com.example.revolutcurrenciesapp.view.currency.model.CurrencyDetails
import com.example.revolutcurrenciesapp.view.currency.model.CurrencyUi
import javax.inject.Inject

class CurrencyModelMapper @Inject constructor() :
    BaseModelMapper<List<Currency>, List<CurrencyUi>>() {

    override fun map(response: List<Currency>): List<CurrencyUi> = response.map {
        CurrencyUi(
            it.name,
            it.amount,
            mapCurrencyDetails(it.name)
        )
    }

    private fun mapCurrencyDetails(currencyName: String): CurrencyDetails {
        val (name, flagIcon) = when (currencyName) {
            "EUR" -> R.string.euro to R.drawable.ic_european_union
            "THB" -> R.string.thai_bahl to R.drawable.ic_thailand
            "JPY" -> R.string.japanese_yen to R.drawable.ic_japan
            "DKK" -> R.string.danish_krone to R.drawable.ic_denmark
            "CZK" -> R.string.czech_koruna to R.drawable.ic_czech_republic
            "USD" -> R.string.us_dollar to R.drawable.ic_united_states
            "AUD" -> R.string.australian_dollar to R.drawable.ic_australia
            "BGN" -> R.string.bulgarian_lev to R.drawable.ic_bulgaria
            "BRL" -> R.string.brazil_real to R.drawable.ic_brazil
            "CAD" -> R.string.canada_dollar to R.drawable.ic_canada
            "CNY" -> R.string.china_yuan to R.drawable.ic_china
            "CHF" -> R.string.switzerland_frank to R.drawable.ic_switzerland
            "GBP" -> R.string.british_pound to R.drawable.ic_united_kingdom
            "HKD" -> R.string.hong_kong_dollar to R.drawable.ic_hong_kong
            "HRK" -> R.string.croatia_kuna to R.drawable.ic_croatia
            "HUF" -> R.string.hungarian_forint to R.drawable.ic_hungary
            "IDR" -> R.string.indonesian_rupiah to R.drawable.ic_indonesia
            "ILS" -> R.string.israeli_shekel to R.drawable.ic_israel
            "INR" -> R.string.india_rupee to R.drawable.ic_india
            "ISK" -> R.string.icelandic_krona to R.drawable.ic_iceland
            "KRW" -> R.string.korea_south_won to R.drawable.ic_south_korea
            "MXN" -> R.string.mexican_peso to R.drawable.ic_mexico
            "MYR" -> R.string.malaysian_ringgit to R.drawable.ic_malaysia
            "NOK" -> R.string.norway_krone to R.drawable.ic_norway
            "NZD" -> R.string.new_zealand_dollar to R.drawable.ic_new_zealand
            "PHP" -> R.string.philippines_peso to R.drawable.ic_philippines
            "PLN" -> R.string.poland_zloty to R.drawable.ic_poland
            "RON" -> R.string.romanian_leu to R.drawable.ic_romania
            "RUB" -> R.string.russian_ruble to R.drawable.ic_russia
            "SEK" -> R.string.sweden_krona to R.drawable.ic_sweden
            "TRY" -> R.string.turkish_lire to R.drawable.ic_turkey
            "SGD" -> R.string.singapore_dollar to R.drawable.ic_singapore
            "ZAR" -> R.string.south_africa_rand to R.drawable.ic_south_africa
            else -> R.string.empty_string to 0
        }

        return CurrencyDetails(name, flagIcon)
    }
}
