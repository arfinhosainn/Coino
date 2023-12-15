package com.example.coino.di

import android.util.Log
import com.example.coino.core.common.BASE_URL
import com.example.coino.feature_coins.data.repositories.remote.CoinGeckoApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {


    @Singleton
    @Provides
    fun providesCoinApi(): CoinGeckoApi {
        return Retrofit.Builder().baseUrl(BASE_URL).client(client)
            .addConverterFactory(MoshiConverterFactory.create())
            .build()
            .create(CoinGeckoApi::class.java)

    }

    val client = OkHttpClient.Builder()
        .addInterceptor(Interceptor { chain ->
            val request = chain.request()
            val url = request.url.toString()
            Log.d("Retrofit", "Requested URL: $url")
            chain.proceed(request)
        })
        .build()






}