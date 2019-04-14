package com.example.revolutcurrenciesapp.model

import com.example.revolutcurrenciesapp.common.adapter.AdapterKeys
import com.example.revolutcurrenciesapp.common.adapter.ViewType

class LoadingModel : ViewType {
    override fun getViewType(): Int = AdapterKeys.LOADING_ITEM
}