package com.revolut.testapp.ui.currency

import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.google.android.material.snackbar.Snackbar
import com.revolut.testapp.R
import com.revolut.testapp.data.remote.CurrencyResult
import com.revolut.testapp.ui.currency.model.CurrencyData
import com.revolut.testapp.util.ImageLoader
import dagger.android.support.DaggerAppCompatActivity
import kotlinx.android.synthetic.main.currency_activity.*
import javax.inject.Inject

class CurrencyActivity : DaggerAppCompatActivity(), CurrencyListListener {

    @Inject lateinit var viewModelFactory: ViewModelProvider.Factory
    @Inject lateinit var imageLoader: ImageLoader

    private lateinit var viewModel: CurrencyViewModel
    private lateinit var currencyAdapter: CurrencyAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.currency_activity)

        viewModel = ViewModelProviders.of(this, viewModelFactory)[CurrencyViewModel::class.java]
        observeViewModelValue()

        currencyAdapter = CurrencyAdapter(mutableListOf(), imageLoader, this)
        currencyRecyclerView.adapter = currencyAdapter
    }

    private fun observeViewModelValue() {
        viewModel.currencyResult.observe(this, Observer { currencyResult ->
            when (currencyResult) {
                is CurrencyResult.Loading -> progressBar.visibility = View.VISIBLE
                is CurrencyResult.Success -> displayCurrencyList(currencyResult.currencyList)
                is CurrencyResult.Error -> displayErrorMessage(currencyResult.message)
            }
        })
    }

    private fun displayCurrencyList(currencyList: MutableList<CurrencyData>) {
        progressBar.visibility = View.GONE

        currencyAdapter.updateCurrencyData(currencyList)
    }

    private fun displayErrorMessage(errorMessage: String) {
        progressBar.visibility = View.GONE

        Snackbar.make(container, errorMessage, Snackbar.LENGTH_LONG).show()
    }

    override fun onCurrencyListUpdated(currencyList: MutableList<CurrencyData>) {
        viewModel.existingList = currencyList
    }
}
