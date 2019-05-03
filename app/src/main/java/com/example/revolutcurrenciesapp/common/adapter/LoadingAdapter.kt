package com.example.revolutcurrenciesapp.common.adapter

import com.example.revolutcurrenciesapp.base.BaseAdapter
import com.example.revolutcurrenciesapp.model.LoadingModel

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