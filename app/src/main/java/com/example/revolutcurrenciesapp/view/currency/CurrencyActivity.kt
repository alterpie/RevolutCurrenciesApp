package com.example.revolutcurrenciesapp.view.currency

import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.revolutcurrenciesapp.R
import com.example.revolutcurrenciesapp.base.*
import com.example.revolutcurrenciesapp.common.adapter.LoadingItemDecoration
import com.example.revolutcurrenciesapp.util.hideKeyboard
import com.example.revolutcurrenciesapp.util.isConnectivityException
import com.example.revolutcurrenciesapp.view.currency.adapter.CurrenciesAdapter
import com.example.revolutcurrenciesapp.view.currency.adapter.CurrencyItemDecoration
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_main.*

class CurrencyActivity : BaseActivity() {
    private val currenciesAdapter: CurrenciesAdapter by lazy {
        CurrenciesAdapter(
            viewModel::setBaseCurrency,
            viewModel::setBaseAmount
        )
    }

    private val viewModel: CurrencyViewModel by lazy {
        ViewModelProviders.of(this, viewModelFactory).get(CurrencyViewModel::class.java)
    }

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
            ).setAction(getString(R.string.retry)) { viewModel.retryLoad() }.show()
        }
    }

    companion object {
        private const val HIDE_KEYBOARD_ITEMS_THRESHOLD = 3
    }
}
