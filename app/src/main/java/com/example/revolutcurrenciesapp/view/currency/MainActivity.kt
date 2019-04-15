package com.example.revolutcurrenciesapp.view.currency

import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.revolutcurrenciesapp.R
import com.example.revolutcurrenciesapp.base.BaseActivity
import com.example.revolutcurrenciesapp.common.adapter.LoadingItemDecoration
import com.example.revolutcurrenciesapp.model.CurrencyModel
import com.example.revolutcurrenciesapp.view.currency.adapter.CurrenciesAdapter
import com.example.revolutcurrenciesapp.view.currency.adapter.CurrencyItemDecoration
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*

class MainActivity : BaseActivity() {
    private val currenciesAdapter: CurrenciesAdapter by lazy {
        CurrenciesAdapter {
            Toast.makeText(this, it.name, Toast.LENGTH_SHORT).show()
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

        val items = mutableListOf<CurrencyModel>().apply {
            repeat(15) {
                add(
                    CurrencyModel(
                        UUID.randomUUID().toString().substring(0..3), 509.0,
                        R.string.app_name,
                        R.mipmap.ic_launcher_round
                    )
                )
            }
        }
        currenciesAdapter.setItems(items)
        currenciesAdapter.addLoadingItem()
    }
}
