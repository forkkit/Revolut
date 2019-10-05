package com.revolut.testapp.data

import com.revolut.testapp.data.remote.model.Rates
import io.reactivex.Observable

interface CurrencyRepository {

    fun retrieveCurrencyRates(base: String): Observable<Rates>
}
