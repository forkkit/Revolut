package com.revolut.testapp.data.remote

import com.revolut.testapp.ui.currency.model.CurrencyData

sealed class CurrencyResult {

    object Loading : CurrencyResult()
    data class Success(val currencyList: MutableList<CurrencyData>) : CurrencyResult()
    data class Error(val message: String) : CurrencyResult()
}
