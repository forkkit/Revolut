package com.revolut.testapp.data.remote

import com.revolut.testapp.data.remote.model.ApiResponse
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

interface CurrencyService {

    @GET("/latest")
    fun retrieveCurrencyRates(@Query("base") base: String): Observable<ApiResponse>
}
