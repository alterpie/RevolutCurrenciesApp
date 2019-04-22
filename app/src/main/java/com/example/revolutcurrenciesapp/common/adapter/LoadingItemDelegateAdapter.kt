package com.example.revolutcurrenciesapp.common.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.revolutcurrenciesapp.R
import com.example.revolutcurrenciesapp.model.LoadingModel

class LoadingItemDelegateAdapter :
    ViewTypeDelegateAdapter<LoadingModel, LoadingItemDelegateAdapter.LoadingItemViewHolder> {
    override fun onBindViewHolder(holder: LoadingItemViewHolder, item: LoadingModel, payloads: MutableList<Any>) {

    }

    override fun onCreateViewHolder(parent: ViewGroup): LoadingItemViewHolder =
        LoadingItemViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_loading, parent, false))

    override fun onBindViewHolder(holder: LoadingItemViewHolder, item: LoadingModel) = Unit

    inner class LoadingItemViewHolder(view: View) : RecyclerView.ViewHolder(view)
}