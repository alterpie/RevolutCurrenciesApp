package com.example.revolutcurrenciesapp.features.currency.adapter

import com.example.revolutcurrenciesapp.base.adapter.BaseDiffUtil
import com.example.revolutcurrenciesapp.features.currency.model.CurrencyUi

class CurrencyDiffUtil(
    oldItems: List<CurrencyUi>,
    newItems: List<CurrencyUi>
) : BaseDiffUtil<CurrencyUi>(oldItems, newItems) {
    override fun changePayload(oldItemPosition: Int, newItemPosition: Int): Any? {
        val newItem = getNewItem(newItemPosition)
        return CurrencyPayload(if (getOldItem(oldItemPosition).amount != newItem.amount) newItem.amount else null)
    }

    override fun itemsSame(oldItemPosition: Int, newItemPosition: Int): Boolean =
        getOldItem(oldItemPosition).name == getNewItem(newItemPosition).name
}
