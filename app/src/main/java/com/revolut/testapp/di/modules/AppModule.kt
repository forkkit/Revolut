package com.revolut.testapp.di.modules

import com.revolut.testapp.util.AppScheduler
import com.revolut.testapp.util.GlideImageLoader
import com.revolut.testapp.util.ImageLoader
import dagger.Module
import dagger.Provides
import dagger.Reusable

@Module
object AppModule {

    @Provides
    @Reusable
    @JvmStatic
    fun provideAppScheduler(): AppScheduler = AppScheduler()

    @Provides
    @Reusable
    @JvmStatic
    fun provideImageLoader(): ImageLoader = GlideImageLoader()
}
