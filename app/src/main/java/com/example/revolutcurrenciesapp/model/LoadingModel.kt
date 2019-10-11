package com.example.revolutcurrenciesapp.model

import com.example.revolutcurrenciesapp.base.adapter.AdapterKeys
import com.example.revolutcurrenciesapp.base.adapter.ViewType

class LoadingModel : ViewType {
    override fun getViewType(): Int = AdapterKeys.LOADING_ITEM
}
