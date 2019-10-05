package com.revolut.testapp.data

import com.nhaarman.mockito_kotlin.verify
import com.nhaarman.mockito_kotlin.whenever
import com.revolut.testapp.data.remote.CurrencyService
import com.revolut.testapp.sampleApiResponse
import com.revolut.testapp.sampleRates
import io.reactivex.Observable
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import java.io.IOException

@RunWith(JUnit4::class)
class CurrencyRepositoryImplTest {

    @Mock lateinit var currencyService: CurrencyService

    private lateinit var currencyRepository: CurrencyRepositoryImpl

    private val base = "EUR"

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)

        this.currencyRepository = CurrencyRepositoryImpl(currencyService)
    }

    @Test
    fun retrieveCurrencyRates_shouldReturnRates_whenRequestIsSuccessful() {
        whenever(currencyService.retrieveCurrencyRates(base)).thenReturn(
            Observable.just(sampleApiResponse)
        )

        currencyRepository.retrieveCurrencyRates(base)
            .test()
            .assertValue(sampleRates)
            .dispose()

        verify(currencyService).retrieveCurrencyRates(base)
    }

    @Test
    fun retrieveCurrencyRates_shouldNotReturnValues_whenRequestHasException() {
        whenever(currencyService.retrieveCurrencyRates(base)).thenReturn(
            Observable.error(IOException())
        )

        currencyRepository.retrieveCurrencyRates(base)
            .test()
            .assertNoValues()
            .dispose()

        verify(currencyService).retrieveCurrencyRates(base)
    }
}
