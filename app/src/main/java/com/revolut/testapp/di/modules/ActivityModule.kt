package com.revolut.testapp.di.modules

import com.revolut.testapp.ui.currency.CurrencyActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityModule {

    @ContributesAndroidInjector
    abstract fun contributeCurrencyActivity(): CurrencyActivity
}
