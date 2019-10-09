package com.example.revolutcurrenciesapp.base.adapter

import androidx.recyclerview.widget.DiffUtil
import com.example.revolutcurrenciesapp.common.adapter.ViewType
import java.lang.reflect.ParameterizedType

abstract class BaseDiffUtil<T : ViewType>(private val oldItems: List<T>, private val newItems: List<T>) :
    DiffUtil.Callback() {

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return if (oldItems[oldItemPosition].javaClass.isAssignableFrom(newItems[newItemPosition].javaClass)
            && oldItems[oldItemPosition].javaClass.isAssignableFrom((javaClass.genericSuperclass as ParameterizedType).actualTypeArguments[0] as Class<T>)
        ) itemsSame(oldItemPosition, newItemPosition)
        else false
    }

    fun getOldItem(pos: Int): T = oldItems[pos]

    fun getNewItem(pos: Int): T = newItems[pos]

    override fun getOldListSize(): Int = oldItems.size

    override fun getNewListSize(): Int = newItems.size

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean =
        contentSame(oldItemPosition, newItemPosition)

    protected open fun contentSame(oldItemPosition: Int, newItemPosition: Int): Boolean =
        oldItems[oldItemPosition] == newItems[newItemPosition]

    abstract fun itemsSame(oldItemPosition: Int, newItemPosition: Int): Boolean

    override fun getChangePayload(oldItemPosition: Int, newItemPosition: Int): Any? =
        changePayload(oldItemPosition, newItemPosition)

    abstract fun changePayload(oldItemPosition: Int, newItemPosition: Int): Any?
}
