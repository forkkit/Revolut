package com.revolut.testapp.ui.currency.model

import com.revolut.testapp.util.Constants.DEFAULT_RATE
import com.revolut.testapp.util.Constants.DEFAULT_VALUE

data class CurrencyData(
    val currencyFlag: Int,
    val currencyCode: String,
    val currency: String,
    var rate: Double = DEFAULT_RATE,
    var value: String = DEFAULT_VALUE
)
