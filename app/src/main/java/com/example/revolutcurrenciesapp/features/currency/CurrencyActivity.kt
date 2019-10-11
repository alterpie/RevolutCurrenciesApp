package com.example.revolutcurrenciesapp.features.currency

import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.revolutcurrenciesapp.R
import com.example.revolutcurrenciesapp.base.*
import com.example.revolutcurrenciesapp.common.adapter.LoadingItemDecoration
import com.example.revolutcurrenciesapp.extensions.hideKeyboard
import com.example.revolutcurrenciesapp.extensions.isConnectivityException
import com.example.revolutcurrenciesapp.features.currency.adapter.CurrenciesAdapter
import com.example.revolutcurrenciesapp.features.currency.adapter.CurrencyItemDecoration
import com.example.revolutcurrenciesapp.features.currency.di.currencyModule
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.android.viewmodel.ext.android.viewModel
import org.koin.core.module.Module

class CurrencyActivity : BaseActivity() {

    override val featureModule: Module
        get() = currencyModule

    private val currenciesAdapter: CurrenciesAdapter by lazy {
        CurrenciesAdapter(
            viewModel::changeBaseCurrency,
            viewModel::setBaseAmount
        )
    }

    private val viewModel: CurrencyViewModel by viewModel()

    override fun getLayoutId(): Int = R.layout.activity_main

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initViews()

        viewModel.state.observe(this, Observer { render(it) })
        observe(viewModel.events, ::handleEvent)
    }

    private fun initViews() {
        recycler_view_currencies.run {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(this@CurrencyActivity).apply {
                addOnScrollListener(object : RecyclerView.OnScrollListener() {
                    override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                        super.onScrolled(recyclerView, dx, dy)
                        if (dy > 0 && this@apply.findFirstVisibleItemPosition() == HIDE_KEYBOARD_ITEMS_THRESHOLD) {
                            hideKeyboard()
                        }
                    }
                })
            }
            adapter = currenciesAdapter
            addItemDecoration(LoadingItemDecoration(this@CurrencyActivity))
            addItemDecoration(CurrencyItemDecoration(this@CurrencyActivity))
        }
    }

    private fun render(state: CurrencyViewState) {
        when (state.screenState) {
            ScreenState.Loading -> currenciesAdapter.addLoadingItem()
            else -> {
                currenciesAdapter.removeLoadingItem()
                currenciesAdapter.setItems(state.currencies)
            }
        }
    }

    override fun handleEvent(event: Event) {
        if (event is ErrorEvent) {
            val message = if (event.throwable.isConnectivityException()) {
                getString(R.string.error_check_connectivity)
            } else {
                getString(R.string.error_unexpected)
            }
            Snackbar.make(
                findViewById<View>(android.R.id.content),
                message,
                Snackbar.LENGTH_INDEFINITE
            ).setAction(getString(R.string.retry)) { viewModel.retry() }.show()
        }
    }

    companion object {
        private const val HIDE_KEYBOARD_ITEMS_THRESHOLD = 3
    }
}
