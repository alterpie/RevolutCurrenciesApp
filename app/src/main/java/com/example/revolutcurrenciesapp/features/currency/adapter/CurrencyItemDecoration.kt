package com.example.revolutcurrenciesapp.features.currency.adapter

import android.content.Context
import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.revolutcurrenciesapp.R
import com.example.revolutcurrenciesapp.base.adapter.AdapterKeys

class CurrencyItemDecoration(context: Context) : RecyclerView.ItemDecoration() {
    private val topMargin = context.resources.getDimensionPixelOffset(R.dimen.margin_unit_x2)
    private val horizontalMargin = context.resources.getDimensionPixelOffset(R.dimen.margin_unit_x2)

    override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State) {
        val position = parent.getChildAdapterPosition(view)
        if (position == RecyclerView.NO_POSITION) return
        val viewType = parent.adapter?.getItemViewType(position)
        if (viewType == AdapterKeys.CURRENCY_ITEM) {
            if (position != 0) outRect.top = topMargin
            outRect.left = horizontalMargin
            outRect.right = horizontalMargin
        }
    }
}
