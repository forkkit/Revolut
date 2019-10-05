package com.revolut.testapp.di.modules

import com.revolut.testapp.BuildConfig
import com.revolut.testapp.data.CurrencyRepository
import com.revolut.testapp.data.CurrencyRepositoryImpl
import com.revolut.testapp.data.remote.CurrencyService
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.Reusable
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory

@Module
object NetworkModule {

    @Provides
    @Reusable
    @JvmStatic
    fun providesCurrencyRepository(currencyService: CurrencyService): CurrencyRepository =
        CurrencyRepositoryImpl(currencyService)

    @Provides
    @Reusable
    @JvmStatic
    fun providesOkHttpClient(): OkHttpClient = OkHttpClient.Builder().build()

    @Provides
    @Reusable
    @JvmStatic
    fun providesMoshi(): Moshi = Moshi.Builder()
                                      .add(KotlinJsonAdapterFactory())
                                      .build()

    @Provides
    @Reusable
    @JvmStatic
    fun providesRetrofit(okHttpClient: OkHttpClient, moshi: Moshi): Retrofit {
        return Retrofit.Builder()
                       .client(okHttpClient)
                       .baseUrl(BuildConfig.BASE_URL)
                       .addConverterFactory(MoshiConverterFactory.create(moshi))
                       .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                       .build()
    }

    @Provides
    @Reusable
    @JvmStatic
    fun providesCurrencyService(retrofit: Retrofit): CurrencyService = retrofit.create(CurrencyService::class.java)
}
