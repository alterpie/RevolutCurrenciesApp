package com.example.revolutcurrenciesapp.view.currency.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.revolutcurrenciesapp.R
import com.example.revolutcurrenciesapp.common.adapter.ViewTypeDelegateAdapter
import com.example.revolutcurrenciesapp.model.CurrencyModel
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.item_currency.*

class CurrencyDelegateAdapter(
    private var items: () -> List<CurrencyModel>,
    private val onItemClick: (CurrencyModel) -> Unit
) :
    ViewTypeDelegateAdapter<CurrencyModel, CurrencyDelegateAdapter.CurrencyViewHolder> {
    override fun onCreateViewHolder(parent: ViewGroup): CurrencyViewHolder =
        CurrencyViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_currency, parent, false))

    override fun onBindViewHolder(holder: CurrencyViewHolder, item: CurrencyModel) =
        holder.bind(item)

    inner class CurrencyViewHolder(view: View) : RecyclerView.ViewHolder(view), LayoutContainer {
        override val containerView: View?
            get() = itemView

        init {
            itemView.setOnClickListener {
                adapterPosition.let {
                    if (it != RecyclerView.NO_POSITION) onItemClick(items()[it])
                }
            }
        }

        fun bind(item: CurrencyModel) {
            text_view_currency_name.text = item.name
            text_view_currency_details.text = text_view_currency_details.context.getString(item.currencyDetails)
            image_view_currency_image.setImageResource(item.countryFlagDrawable)
            edit_text_amount.setText(item.amount.toString())
            edit_text_amount.setSelection(edit_text_amount.length())
        }
    }
}