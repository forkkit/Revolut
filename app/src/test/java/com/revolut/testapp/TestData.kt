package com.revolut.testapp

import com.revolut.testapp.data.remote.model.ApiResponse
import com.revolut.testapp.data.remote.model.Rates
import com.revolut.testapp.util.Constants.DEFAULT_RATE
import com.revolut.testapp.util.Constants.DEFAULT_VALUE

val sampleRates = Rates(
    aUD = DEFAULT_RATE,
    bGN = DEFAULT_RATE,
    bRL = DEFAULT_RATE,
    cAD = DEFAULT_RATE,
    cHF = DEFAULT_RATE,
    cNY = DEFAULT_RATE,
    cZK = DEFAULT_RATE,
    dKK = DEFAULT_RATE,
    eUR = DEFAULT_RATE,
    gBP = DEFAULT_RATE,
    hKD = DEFAULT_RATE,
    hRK = DEFAULT_RATE,
    hUF = DEFAULT_RATE,
    iDR = DEFAULT_RATE,
    iLS = DEFAULT_RATE,
    iNR = DEFAULT_RATE,
    iSK = DEFAULT_RATE,
    jPY = DEFAULT_RATE,
    kRW = DEFAULT_RATE,
    mXN = DEFAULT_RATE,
    mYR = DEFAULT_RATE,
    nOK = DEFAULT_RATE,
    nZD = DEFAULT_RATE,
    pHP = DEFAULT_RATE,
    pLN = DEFAULT_RATE,
    rON = DEFAULT_RATE,
    rUB = DEFAULT_RATE,
    sEK = DEFAULT_RATE,
    sGD = DEFAULT_RATE,
    tHB = DEFAULT_RATE,
    tRY = DEFAULT_RATE,
    uSD = DEFAULT_RATE,
    zAR = DEFAULT_RATE
)

val sampleApiResponse = ApiResponse("EUR", DEFAULT_VALUE, sampleRates)
