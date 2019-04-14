package com.example.revolutcurrenciesapp.view.currency.adapter

import com.example.revolutcurrenciesapp.base.BaseDiffUtil
import com.example.revolutcurrenciesapp.model.CurrencyModel

class CurrencyDiffUtil(
    oldItems: List<CurrencyModel>,
    newItems: List<CurrencyModel>
) : BaseDiffUtil<CurrencyModel>(oldItems, newItems) {
    override fun itemsSame(oldItemPosition: Int, newItemPosition: Int): Boolean =
            getOldItem(oldItemPosition).name == getNewItem(newItemPosition).name
}