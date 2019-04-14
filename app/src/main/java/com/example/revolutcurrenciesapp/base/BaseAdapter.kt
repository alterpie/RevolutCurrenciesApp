package com.example.revolutcurrenciesapp.base

import android.view.ViewGroup
import androidx.collection.SparseArrayCompat
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.revolutcurrenciesapp.common.adapter.ViewType
import com.example.revolutcurrenciesapp.common.adapter.ViewTypeDelegateAdapter
import com.example.revolutcurrenciesapp.model.LoadingModel

abstract class BaseAdapter<T : ViewType> : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    protected var itemList: List<T> = emptyList()
    protected val delegateAdapters = SparseArrayCompat<ViewTypeDelegateAdapter<*, *>>()

    override fun getItemCount(): Int = itemList.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder =
        delegateAdapters[viewType]?.onCreateViewHolder(parent)
            ?: throw IllegalArgumentException("Unknown viewType: $viewType")

    @Suppress("UNCHECKED_CAST")
    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) =
        (delegateAdapters[getItemViewType(position)] as ViewTypeDelegateAdapter<ViewType, RecyclerView.ViewHolder>)
            .onBindViewHolder(holder, itemList[position])

    override fun getItemViewType(position: Int): Int = itemList[position].getViewType()

    open fun setItems(items: List<T>) {
        removeLoadingItem()
        val diffCallback = getDiffUtil(itemList, items)
        val diffResult = DiffUtil.calculateDiff(diffCallback)
        itemList = items
        diffResult.dispatchUpdatesTo(this)
    }

    abstract fun getDiffUtil(oldItems: List<T>, newItems: List<T>): BaseDiffUtil<T>

    @Suppress("UNCHECKED_CAST")
    open fun addItem(item: ViewType) {
        val oldItems = itemList
        itemList = mutableListOf<T>().apply {
            addAll(itemList)
            add(item as T)
        }
        val diffCallback = getDiffUtil(oldItems, itemList)
        val diffResult = DiffUtil.calculateDiff(diffCallback)
        diffResult.dispatchUpdatesTo(this)
    }

    open fun <I : ViewType> removeItem(item: Class<I>) {
        val oldItems = itemList
        itemList = mutableListOf<T>().apply {
            addAll(itemList)
            removeAll {
                it.javaClass.isAssignableFrom(item)
            }
        }
        val diffCallback = getDiffUtil(oldItems, itemList)
        val diffResult = DiffUtil.calculateDiff(diffCallback)
        diffResult.dispatchUpdatesTo(this)
    }

    open fun removeLoadingItem() {
        removeItem(LoadingModel::class.java)
    }

    open fun addLoadingItem() {
        addItem(LoadingModel())
    }
}