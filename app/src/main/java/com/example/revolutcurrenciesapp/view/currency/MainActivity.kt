package com.example.revolutcurrenciesapp.view.currency

import android.os.Bundle
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.revolutcurrenciesapp.R
import com.example.revolutcurrenciesapp.base.BaseActivity
import com.example.revolutcurrenciesapp.common.adapter.LoadingItemDecoration
import com.example.revolutcurrenciesapp.view.currency.adapter.CurrenciesAdapter
import com.example.revolutcurrenciesapp.view.currency.adapter.CurrencyItemDecoration
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivity() {
    private val currenciesAdapter: CurrenciesAdapter by lazy {
        CurrenciesAdapter {
            viewModel.setBaseCurrency(it.name)
        }
    }

    private val viewModel: MainViewModel by lazy {
        ViewModelProviders.of(this, viewModelFactory).get(MainViewModel::class.java)
    }

    override fun getLayoutId(): Int = R.layout.activity_main

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        recycler_view_currencies.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = currenciesAdapter
            addItemDecoration(LoadingItemDecoration(this@MainActivity))
            addItemDecoration(CurrencyItemDecoration(this@MainActivity))
        }

        viewModel.loadCurrenciesAction.observe(this, currenciesAdapter::setItems, {

        }, this::displayProgress)

        viewModel.loadCurrencies()
    }

    private fun displayProgress(display: Boolean) {
        if (display) {
            if (viewModel.loadCurrenciesAction.getValueData().isNullOrEmpty()) currenciesAdapter.addLoadingItem()
        } else currenciesAdapter.removeLoadingItem()
    }
}
