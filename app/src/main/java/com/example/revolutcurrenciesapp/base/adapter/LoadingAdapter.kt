package com.example.revolutcurrenciesapp.base.adapter

import com.example.revolutcurrenciesapp.common.adapter.LoadingModel

abstract class LoadingAdapter<T : ViewType> : BaseAdapter<T>() {

    open fun removeLoadingItem() {
        removeItem(LoadingModel::class.java)
    }

    open fun addLoadingItem() {
        addItem(LoadingModel())
    }

    override fun setItems(items: List<T>) {
        removeLoadingItem()
        super.setItems(items)
    }
}
