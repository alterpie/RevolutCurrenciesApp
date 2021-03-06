package com.example.revolutcurrenciesapp.view.currency.adapter

import com.example.revolutcurrenciesapp.base.adapter.AdapterKeys
import com.example.revolutcurrenciesapp.base.adapter.BaseDiffUtil
import com.example.revolutcurrenciesapp.common.adapter.LoadingAdapter
import com.example.revolutcurrenciesapp.common.adapter.LoadingItemDelegateAdapter
import com.example.revolutcurrenciesapp.view.currency.model.CurrencyUi

class CurrenciesAdapter(
    onItemClick: (CurrencyUi) -> Unit,
    onAmountChanged: (Double) -> Unit
) : LoadingAdapter<CurrencyUi>() {
    override fun getDiffUtil(
        oldItems: List<CurrencyUi>,
        newItems: List<CurrencyUi>
    ): BaseDiffUtil<CurrencyUi> =
        CurrencyDiffUtil(oldItems, newItems)

    init {
        delegateAdapters.put(
            AdapterKeys.CURRENCY_ITEM,
            CurrencyDelegateAdapter({ itemList }, onItemClick, onAmountChanged)
        )
        delegateAdapters.put(
            AdapterKeys.LOADING_ITEM,
            LoadingItemDelegateAdapter()
        )
    }
}
