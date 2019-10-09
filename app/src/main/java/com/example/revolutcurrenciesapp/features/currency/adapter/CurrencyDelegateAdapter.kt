package com.example.revolutcurrenciesapp.features.currency.adapter

import android.text.Editable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.revolutcurrenciesapp.R
import com.example.revolutcurrenciesapp.common.adapter.ViewTypeDelegateAdapter
import com.example.revolutcurrenciesapp.features.currency.model.CurrencyUi
import com.example.revolutcurrenciesapp.util.TextWatcherAbstract
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.item_currency.*
import java.text.DecimalFormat


class CurrencyDelegateAdapter(
    private var items: () -> List<CurrencyUi>,
    private val onItemClick: (CurrencyUi) -> Unit,
    private val onAmountChanged: (Double) -> Unit
) :
    ViewTypeDelegateAdapter<CurrencyUi, CurrencyDelegateAdapter.CurrencyViewHolder> {

    override fun onBindViewHolder(
        holder: CurrencyViewHolder,
        item: CurrencyUi,
        payloads: MutableList<Any>
    ) {
        holder.bind(payloads[0] as CurrencyPayload)
    }

    override fun onCreateViewHolder(parent: ViewGroup): CurrencyViewHolder =
        CurrencyViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_currency,
                parent,
                false
            )
        )

    override fun onBindViewHolder(holder: CurrencyViewHolder, item: CurrencyUi) =
        holder.bind(item)

    inner class CurrencyViewHolder(view: View) : RecyclerView.ViewHolder(view), LayoutContainer {
        override val containerView: View?
            get() = itemView

        var df = DecimalFormat("#.##")
        var cursorPosition = 0

        init {
            itemView.setOnClickListener {
                adapterPosition.let {
                    if (it != RecyclerView.NO_POSITION) onItemClick(items()[it])
                }
            }
            edit_text_amount.addTextChangedListener(object : TextWatcherAbstract() {
                override fun beforeTextChanged(
                    s: CharSequence?,
                    start: Int,
                    count: Int,
                    after: Int
                ) {
                    cursorPosition = edit_text_amount.selectionStart
                }

                override fun afterTextChanged(s: Editable?) {
                    if (adapterPosition == 0)
                        onAmountChanged(s?.toString()?.toDoubleOrNull() ?: 0.0)
                }
            })
        }

        fun bind(item: CurrencyUi) {
            edit_text_amount.setText(df.format(item.amount))
            if (cursorPosition <= edit_text_amount.length())
                edit_text_amount.setSelection(cursorPosition)

            text_view_currency_name.text = item.name
            text_view_currency_details.text =
                text_view_currency_details.context.getString(item.currencyDetails.name)
            image_view_currency_image.setImageResource(item.currencyDetails.icon)
        }

        fun bind(currencyPayload: CurrencyPayload) {
            if (currencyPayload.amount != null) {
                edit_text_amount.setText(df.format(currencyPayload.amount))
                if (cursorPosition <= edit_text_amount.length())
                    edit_text_amount.setSelection(cursorPosition)
            }
        }
    }
}
