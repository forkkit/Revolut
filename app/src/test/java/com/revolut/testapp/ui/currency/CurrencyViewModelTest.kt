package com.revolut.testapp.ui.currency

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.nhaarman.mockito_kotlin.verify
import com.nhaarman.mockito_kotlin.whenever
import com.revolut.testapp.data.CurrencyRepository
import com.revolut.testapp.data.remote.CurrencyResult
import com.revolut.testapp.sampleRates
import com.revolut.testapp.util.AppScheduler
import io.reactivex.Observable
import io.reactivex.plugins.RxJavaPlugins
import io.reactivex.schedulers.Schedulers
import io.reactivex.schedulers.TestScheduler
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import java.util.concurrent.TimeUnit

@RunWith(JUnit4::class)
class CurrencyViewModelTest {

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock lateinit var appScheduler: AppScheduler
    @Mock lateinit var currencyRepository: CurrencyRepository
    @Mock lateinit var observer: Observer<CurrencyResult>

    private lateinit var currencyViewModel: CurrencyViewModel

    private val testScheduler = TestScheduler()
    private val base = "EUR"

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)

        RxJavaPlugins.setComputationSchedulerHandler { testScheduler }
    }

    @Test
    fun currencyResult_shouldReturnLoading_whenViewModelIsInitialized() {
        whenever(appScheduler.main()).thenReturn(Schedulers.trampoline())
        whenever(appScheduler.io()).thenReturn(Schedulers.trampoline())
        whenever(currencyRepository.retrieveCurrencyRates(base)).thenReturn(Observable.just(sampleRates))

        currencyViewModel = CurrencyViewModel(appScheduler, currencyRepository)

        assertEquals(currencyViewModel.currencyResult.value, CurrencyResult.Loading)
    }

    @Test
    fun retrieveData_shouldReturnSuccess_whenRequestIsSuccessful() {
        whenever(appScheduler.main()).thenReturn(Schedulers.trampoline())
        whenever(appScheduler.io()).thenReturn(Schedulers.trampoline())
        whenever(currencyRepository.retrieveCurrencyRates(base)).thenReturn(Observable.just(sampleRates))

        currencyViewModel = CurrencyViewModel(appScheduler, currencyRepository)
        currencyViewModel.currencyResult.observeForever(observer)

        testScheduler.advanceTimeBy(1, TimeUnit.SECONDS)
        verify(observer).onChanged(CurrencyResult.Success(currencyViewModel.existingList))
        verify(currencyRepository).retrieveCurrencyRates(base)
    }

    @Test
    fun retrieveCurrencyList_shouldReturnError_whenRequestFails() {
        val error = Throwable("Error response")

        whenever(appScheduler.main()).thenReturn(Schedulers.trampoline())
        whenever(appScheduler.io()).thenReturn(Schedulers.trampoline())
        whenever(currencyRepository.retrieveCurrencyRates(base)).thenReturn(Observable.error(error))

        currencyViewModel = CurrencyViewModel(appScheduler, currencyRepository)
        currencyViewModel.currencyResult.observeForever(observer)

        testScheduler.advanceTimeBy(1, TimeUnit.SECONDS)
        verify(observer).onChanged(CurrencyResult.Error("Message: ${error.message}"))
        verify(currencyRepository).retrieveCurrencyRates(base)
    }
}
