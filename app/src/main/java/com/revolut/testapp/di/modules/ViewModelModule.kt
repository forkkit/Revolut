package com.revolut.testapp.di.modules

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.revolut.testapp.di.ViewModelFactory
import com.revolut.testapp.di.ViewModelKey
import com.revolut.testapp.ui.currency.CurrencyViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelModule {

    @Binds
    internal abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory

    @Binds
    @IntoMap
    @ViewModelKey(CurrencyViewModel::class)
    internal abstract fun currencyViewModel(viewModel: CurrencyViewModel): ViewModel
}
