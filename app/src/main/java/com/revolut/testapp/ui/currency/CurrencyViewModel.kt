package com.revolut.testapp.ui.currency

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.revolut.testapp.data.CurrencyRepository
import com.revolut.testapp.data.remote.CurrencyResult
import com.revolut.testapp.ui.currency.model.CurrencyData
import com.revolut.testapp.ui.currency.model.CurrencyDataHelper
import com.revolut.testapp.ui.currency.model.CurrencyDataHelper.eur
import com.revolut.testapp.util.AppScheduler
import io.reactivex.Observable
import io.reactivex.disposables.CompositeDisposable
import java.util.concurrent.TimeUnit
import javax.inject.Inject

private const val ONE_SECOND = 1L
private const val INITIAL_BASE_CURRENCY_RATE = 10.0
private const val INITIAL_BASE_CURRENCY_VALUE = "10"

class CurrencyViewModel @Inject constructor(
    private val appScheduler: AppScheduler,
    private val currencyRepository: CurrencyRepository
) : ViewModel() {

    private var _currencyResult = MutableLiveData<CurrencyResult>()
    private var subscription = CompositeDisposable()
    var existingList = mutableListOf<CurrencyData>()

    private val baseCurrency: CurrencyData
        get() {
            return if (existingList.isEmpty()) {
                eur.apply {
                    rate = INITIAL_BASE_CURRENCY_RATE
                    value = INITIAL_BASE_CURRENCY_VALUE
                }
            } else {
                existingList[0]
            }
        }

    val currencyResult: LiveData<CurrencyResult>
        get() = _currencyResult

    init {
        _currencyResult.value = CurrencyResult.Loading

        retrieveData()
    }

    private fun retrieveData() {
        subscription.add(
            Observable.interval(ONE_SECOND, TimeUnit.SECONDS)
                .flatMap { currencyRepository.retrieveCurrencyRates(baseCurrency.currencyCode) }
                .observeOn(appScheduler.main())
                .subscribeOn(appScheduler.io())
                .subscribe(
                    { currencyRates ->
                        existingList = CurrencyDataHelper.getUpdatedCurrencyList(
                            currencyRates, baseCurrency, existingList
                        )
                        _currencyResult.value = CurrencyResult.Success(existingList)
                    },
                    { error ->
                        _currencyResult.value = CurrencyResult.Error("Message: ${error.message}")
                    })
        )
    }

    override fun onCleared() {
        subscription.clear()

        super.onCleared()
    }
}
