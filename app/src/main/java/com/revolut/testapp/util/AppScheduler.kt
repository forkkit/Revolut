package com.revolut.testapp.util

import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class AppScheduler {

    fun io() = Schedulers.io()
    
    fun main(): Scheduler = AndroidSchedulers.mainThread()
}
