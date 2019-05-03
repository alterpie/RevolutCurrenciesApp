package com.example.revolutcurrenciesapp.view.currency

import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.domain.error.AppError
import com.example.revolutcurrenciesapp.R
import com.example.revolutcurrenciesapp.base.BaseActivity
import com.example.revolutcurrenciesapp.common.adapter.LoadingItemDecoration
import com.example.revolutcurrenciesapp.model.CurrencyModel
import com.example.revolutcurrenciesapp.util.hideKeyboard
import com.example.revolutcurrenciesapp.util.transformToMessage
import com.example.revolutcurrenciesapp.view.currency.adapter.CurrenciesAdapter
import com.example.revolutcurrenciesapp.view.currency.adapter.CurrencyItemDecoration
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivity() {
    private val currenciesAdapter: CurrenciesAdapter by lazy {
        CurrenciesAdapter({ viewModel.setBaseCurrency(it) },
            { viewModel.setBaseAmount(it) })
    }

    private val viewModel: MainViewModel by lazy {
        ViewModelProviders.of(this, viewModelFactory).get(MainViewModel::class.java)
    }

    override fun getLayoutId(): Int = R.layout.activity_main

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        recycler_view_currencies.run {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(this@MainActivity).apply {
                addOnScrollListener(object : RecyclerView.OnScrollListener() {
                    override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                        super.onScrolled(recyclerView, dx, dy)
                        if (dy > 0 && this@apply.findFirstVisibleItemPosition() == HIDE_KEYBOARD_ITEMS_THRESHOLD) hideKeyboard()
                    }
                })
            }
            adapter = currenciesAdapter
            addItemDecoration(LoadingItemDecoration(this@MainActivity))
            addItemDecoration(CurrencyItemDecoration(this@MainActivity))
        }

        viewModel.loadCurrenciesAction.observe(
            this, {
                currenciesAdapter.setItems(it)
            },
            this::handleError,
            this::displayProgress
        )

        val initialCurrency = CurrencyModel("EUR", 100.0, R.string.euro, R.drawable.ic_european_union)

        viewModel.loadCurrencies(initialCurrency.name, initialCurrency.amount)
    }

    private fun handleError(appError: AppError) {
        Snackbar.make(
            findViewById<View>(android.R.id.content),
            appError.transformToMessage(this),
            Snackbar.LENGTH_INDEFINITE
        ).setAction(getString(R.string.retry)) { viewModel.retryLoad() }.show()
    }

    private fun displayProgress(display: Boolean) {
        if (display) {
            if (currenciesAdapter.itemCount == 0) currenciesAdapter.addLoadingItem()
        } else currenciesAdapter.removeLoadingItem()
    }

    companion object {
        private const val HIDE_KEYBOARD_ITEMS_THRESHOLD = 3
    }
}
