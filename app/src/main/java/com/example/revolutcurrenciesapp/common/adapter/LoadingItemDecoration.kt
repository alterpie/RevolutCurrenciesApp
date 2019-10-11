package com.example.revolutcurrenciesapp.common.adapter

import android.content.Context
import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.revolutcurrenciesapp.R
import com.example.revolutcurrenciesapp.base.adapter.AdapterKeys


class LoadingItemDecoration(context: Context) : RecyclerView.ItemDecoration() {

    private val verticalMargin = context.resources.getDimensionPixelSize(R.dimen.margin_unit)

    override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State) {
        val position = parent.getChildAdapterPosition(view)
        if (position == RecyclerView.NO_POSITION) return
        val viewType = parent.adapter?.getItemViewType(position)
        if (viewType == AdapterKeys.LOADING_ITEM) outRect.top = verticalMargin
    }
}
