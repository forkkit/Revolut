package com.revolut.testapp.ui.currency

import com.revolut.testapp.ui.currency.model.CurrencyData

interface CurrencyListListener {

    fun onCurrencyListUpdated(currencyList: MutableList<CurrencyData>)
}
