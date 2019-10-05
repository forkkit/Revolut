package com.revolut.testapp.ui.currency.model

import com.revolut.testapp.R
import com.revolut.testapp.sampleRates
import com.revolut.testapp.util.Constants.EUR
import com.revolut.testapp.util.Constants.eurName
import org.junit.Assert.assertEquals
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class CurrencyDataHelperTest {

    @Test
    fun getUpdatedCurrencyList_shouldReturnListWithBaseCurrencyInFirstPosition() {
        val sampleRates = sampleRates.copy(eUR = null)
        val baseCurrency = CurrencyData(R.drawable.eur, EUR, eurName, 5.00, "5")
        val eur = CurrencyDataHelper.eur.apply {
            rate = 5.00
            value = "5"
        }

        val result = CurrencyDataHelper.getUpdatedCurrencyList(
            rates = sampleRates,
            baseCurrency = eur,
            existingList = mutableListOf()
        )

        assertEquals(result.size, 33)
        assertEquals(result[0], baseCurrency)
    }
}
