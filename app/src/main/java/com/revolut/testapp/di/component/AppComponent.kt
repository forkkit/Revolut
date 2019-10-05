package com.revolut.testapp.di.component

import android.app.Application
import com.revolut.testapp.RevolutApplication
import com.revolut.testapp.di.modules.ActivityModule
import com.revolut.testapp.di.modules.AppModule
import com.revolut.testapp.di.modules.NetworkModule
import com.revolut.testapp.di.modules.ViewModelModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.AndroidInjector
import javax.inject.Singleton

@Singleton
@Component(modules = [AndroidInjectionModule::class,
                      AppModule::class,
                      ActivityModule::class,
                      NetworkModule::class,
                      ViewModelModule::class])
interface AppComponent : AndroidInjector<RevolutApplication> {

    @Component.Builder
    interface Builder {

        fun build(): AppComponent

        @BindsInstance
        fun application(application: Application): Builder
    }
}
