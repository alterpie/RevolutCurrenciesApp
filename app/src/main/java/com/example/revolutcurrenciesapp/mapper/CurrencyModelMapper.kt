package com.example.revolutcurrenciesapp.mapper

import com.example.domain.CurrencyDomain
import com.example.revolutcurrenciesapp.R
import com.example.revolutcurrenciesapp.model.CurrencyModel
import javax.inject.Inject

class CurrencyModelMapper @Inject constructor() : BaseModelMapper<CurrencyDomain, List<CurrencyModel>>() {
    override fun map(response: CurrencyDomain): List<CurrencyModel> = response.rates.map {
        CurrencyModel(it.name, it.amount, R.string.app_name, 0)
    }
}