package com.example.revolutcurrenciesapp.common.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

interface ViewTypeDelegateAdapter<T : ViewType, VH : RecyclerView.ViewHolder> {
    fun onCreateViewHolder(parent: ViewGroup): VH
    fun onBindViewHolder(holder: VH, item: T)
}