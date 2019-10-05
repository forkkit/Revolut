package com.revolut.testapp.ui.currency.model

import com.revolut.testapp.R
import com.revolut.testapp.data.remote.model.Rates
import com.revolut.testapp.util.Constants

object CurrencyDataHelper {

    private const val TWO_DECIMAL_PLACES = "%.2f"

    private val aud = CurrencyData(R.drawable.aud, Constants.AUD, Constants.audName)
    private val bgn = CurrencyData(R.drawable.bgn, Constants.BGN, Constants.bgnName)
    private val brl = CurrencyData(R.drawable.brl, Constants.BRL, Constants.brlName)
    private val cad = CurrencyData(R.drawable.cad, Constants.CAD, Constants.cadName)
    private val chf = CurrencyData(R.drawable.chf, Constants.CHF, Constants.chfName)
    private val cny = CurrencyData(R.drawable.cny, Constants.CNY, Constants.cnyName)
    private val czk = CurrencyData(R.drawable.czk, Constants.CZK, Constants.czkName)
    private val dkk = CurrencyData(R.drawable.dkk, Constants.DKK, Constants.dkkName)
    val eur = CurrencyData(R.drawable.eur, Constants.EUR, Constants.eurName)
    private val gbp = CurrencyData(R.drawable.gbp, Constants.GBP, Constants.gbpName)
    private val hkd = CurrencyData(R.drawable.hkd, Constants.HKD, Constants.hkdName)
    private val hrk = CurrencyData(R.drawable.hrk, Constants.HRK, Constants.hrkName)
    private val huf = CurrencyData(R.drawable.huf, Constants.HUF, Constants.hufName)
    private val idr = CurrencyData(R.drawable.idr, Constants.IDR, Constants.idrName)
    private val ils = CurrencyData(R.drawable.ils, Constants.ILS, Constants.ilsName)
    private val inr = CurrencyData(R.drawable.inr, Constants.INR, Constants.inrName)
    private val isk = CurrencyData(R.drawable.isk, Constants.ISK, Constants.iskName)
    private val jpy = CurrencyData(R.drawable.jpy, Constants.JPY, Constants.jpyName)
    private val krw = CurrencyData(R.drawable.krw, Constants.KRW, Constants.krwName)
    private val mxn = CurrencyData(R.drawable.mxn, Constants.MXN, Constants.mxnName)
    private val myr = CurrencyData(R.drawable.myr, Constants.MYR, Constants.myrName)
    private val nok = CurrencyData(R.drawable.nok, Constants.NOK, Constants.nokName)
    private val nzd = CurrencyData(R.drawable.nzd, Constants.NZD, Constants.nzdName)
    private val php = CurrencyData(R.drawable.php, Constants.PHP, Constants.phpName)
    private val pln = CurrencyData(R.drawable.pln, Constants.PLN, Constants.plnName)
    private val ron = CurrencyData(R.drawable.ron, Constants.RON, Constants.ronName)
    private val rub = CurrencyData(R.drawable.rub, Constants.RUB, Constants.rubName)
    private val sek = CurrencyData(R.drawable.sek, Constants.SEK, Constants.sekName)
    private val sgd = CurrencyData(R.drawable.sgd, Constants.SGD, Constants.sgdName)
    private val thb = CurrencyData(R.drawable.thb, Constants.THB, Constants.thbName)
    private val tury = CurrencyData(R.drawable.tury, Constants.TRY, Constants.tryName)
    private val usd = CurrencyData(R.drawable.usd, Constants.USD, Constants.usdName)
    private val zar = CurrencyData(R.drawable.zar, Constants.ZAR, Constants.zarName)

    fun getUpdatedCurrencyList(rates: Rates, baseCurrency: CurrencyData, existingList: MutableList<CurrencyData>): MutableList<CurrencyData> {
        updateRates(rates, baseCurrency.rate)
        updateValues(baseCurrency)

        val currencyList = mutableListOf(
            aud, bgn, brl, cad, chf, cny, czk, dkk, eur, gbp, hkd, hrk, huf, idr, ils, inr, isk, jpy,
            krw, mxn, myr, nok, nzd, php, pln, ron, rub, sek, sgd, thb, tury, usd, zar)

        val updatedList = moveBaseCurrencyToTopOfCurrencyList(currencyList, baseCurrency)

        if (existingList.isEmpty()) {
            existingList.addAll(updatedList)
        } else {
            existingList.forEach { currencyData ->
                currencyData.rate = updatedList.first { it.currencyCode == currencyData.currencyCode }.rate
                currencyData.value = updatedList.first { it.currencyCode == currencyData.currencyCode }.value
            }
        }

        return existingList
    }

    private fun updateRates(rates: Rates, baseCurrencyRate: Double) {
        aud.rate = getRate(rates.aUD, baseCurrencyRate)
        bgn.rate = getRate(rates.bGN, baseCurrencyRate)
        brl.rate = getRate(rates.bRL, baseCurrencyRate)
        cad.rate = getRate(rates.cAD, baseCurrencyRate)
        chf.rate = getRate(rates.cHF, baseCurrencyRate)
        cny.rate = getRate(rates.cNY, baseCurrencyRate)
        czk.rate = getRate(rates.cZK, baseCurrencyRate)
        dkk.rate = getRate(rates.dKK, baseCurrencyRate)
        eur.rate = getRate(rates.eUR, baseCurrencyRate)
        gbp.rate = getRate(rates.gBP, baseCurrencyRate)
        hkd.rate = getRate(rates.hKD, baseCurrencyRate)
        hrk.rate = getRate(rates.hRK, baseCurrencyRate)
        huf.rate = getRate(rates.hUF, baseCurrencyRate)
        idr.rate = getRate(rates.iDR, baseCurrencyRate)
        ils.rate = getRate(rates.iLS, baseCurrencyRate)
        inr.rate = getRate(rates.iNR, baseCurrencyRate)
        isk.rate = getRate(rates.iSK, baseCurrencyRate)
        jpy.rate = getRate(rates.jPY, baseCurrencyRate)
        krw.rate = getRate(rates.kRW, baseCurrencyRate)
        mxn.rate = getRate(rates.mXN, baseCurrencyRate)
        myr.rate = getRate(rates.mYR, baseCurrencyRate)
        nok.rate = getRate(rates.nOK, baseCurrencyRate)
        nzd.rate = getRate(rates.nZD, baseCurrencyRate)
        php.rate = getRate(rates.pHP, baseCurrencyRate)
        pln.rate = getRate(rates.pLN, baseCurrencyRate)
        ron.rate = getRate(rates.rON, baseCurrencyRate)
        rub.rate = getRate(rates.rUB, baseCurrencyRate)
        sek.rate = getRate(rates.sEK, baseCurrencyRate)
        sgd.rate = getRate(rates.sGD, baseCurrencyRate)
        thb.rate = getRate(rates.tHB, baseCurrencyRate)
        tury.rate = getRate(rates.tRY, baseCurrencyRate)
        usd.rate = getRate(rates.uSD, baseCurrencyRate)
        zar.rate = getRate(rates.zAR, baseCurrencyRate)
    }

    private fun getRate(rate: Double?, baseCurrencyRate: Double) = rate ?: baseCurrencyRate

    private fun updateValues(baseCurrency: CurrencyData) {
        aud.value = getValue(aud, baseCurrency)
        bgn.value = getValue(bgn, baseCurrency)
        brl.value = getValue(brl, baseCurrency)
        cad.value = getValue(cad, baseCurrency)
        chf.value = getValue(chf, baseCurrency)
        cny.value = getValue(cny, baseCurrency)
        czk.value = getValue(czk, baseCurrency)
        dkk.value = getValue(dkk, baseCurrency)
        eur.value = getValue(eur, baseCurrency)
        gbp.value = getValue(gbp, baseCurrency)
        hkd.value = getValue(hkd, baseCurrency)
        hrk.value = getValue(hrk, baseCurrency)
        huf.value = getValue(huf, baseCurrency)
        idr.value = getValue(idr, baseCurrency)
        ils.value = getValue(ils, baseCurrency)
        inr.value = getValue(inr, baseCurrency)
        isk.value = getValue(isk, baseCurrency)
        jpy.value = getValue(jpy, baseCurrency)
        krw.value = getValue(krw, baseCurrency)
        mxn.value = getValue(mxn, baseCurrency)
        myr.value = getValue(myr, baseCurrency)
        nok.value = getValue(nok, baseCurrency)
        nzd.value = getValue(nzd, baseCurrency)
        php.value = getValue(php, baseCurrency)
        pln.value = getValue(pln, baseCurrency)
        ron.value = getValue(ron, baseCurrency)
        rub.value = getValue(rub, baseCurrency)
        sek.value = getValue(sek, baseCurrency)
        sgd.value = getValue(sgd, baseCurrency)
        thb.value = getValue(thb, baseCurrency)
        tury.value = getValue(tury, baseCurrency)
        usd.value = getValue(usd, baseCurrency)
        zar.value = getValue(zar, baseCurrency)
    }

    private fun getValue(currency: CurrencyData, baseCurrency: CurrencyData): String {
        val isBaseCurrency = currency.currencyCode == baseCurrency.currencyCode

        return if (isBaseCurrency) {
            baseCurrency.value
        } else {
            String.format(TWO_DECIMAL_PLACES, currency.rate.times(baseCurrency.rate))
        }
    }

    private fun moveBaseCurrencyToTopOfCurrencyList(
        currencyList: MutableList<CurrencyData>,
        baseCurrency: CurrencyData
    ): List<CurrencyData> {
        currencyList.remove(baseCurrency).also { currencyList.add(0, baseCurrency) }

        return currencyList
    }
}
