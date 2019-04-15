package com.example.revolutcurrenciesapp.view.currency.adapter

import com.example.revolutcurrenciesapp.base.BaseAdapter
import com.example.revolutcurrenciesapp.base.BaseDiffUtil
import com.example.revolutcurrenciesapp.common.adapter.AdapterKeys
import com.example.revolutcurrenciesapp.common.adapter.LoadingItemDelegateAdapter
import com.example.revolutcurrenciesapp.model.CurrencyModel

class CurrenciesAdapter(onItemClick: (CurrencyModel) -> Unit) : BaseAdapter<CurrencyModel>() {
    override fun getDiffUtil(
        oldItems: List<CurrencyModel>,
        newItems: List<CurrencyModel>
    ): BaseDiffUtil<CurrencyModel> =
        CurrencyDiffUtil(oldItems, newItems)

    init {
        delegateAdapters.put(AdapterKeys.CURRENCY_ITEM,
            CurrencyDelegateAdapter({ itemList }, onItemClick)
        )
        delegateAdapters.put(
            AdapterKeys.LOADING_ITEM,
            LoadingItemDelegateAdapter()
        )
    }
}