package com.revolut.testapp.data

import com.revolut.testapp.data.remote.CurrencyService
import com.revolut.testapp.data.remote.model.Rates
import io.reactivex.Observable
import javax.inject.Inject

class CurrencyRepositoryImpl @Inject constructor(
    private val currencyService: CurrencyService
) : CurrencyRepository {

    override fun retrieveCurrencyRates(base: String): Observable<Rates> =
        currencyService.retrieveCurrencyRates(base).map { it.rates }
}
