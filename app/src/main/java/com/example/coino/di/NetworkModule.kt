package com.example.coino.di

import com.example.coino.core.util.BASE_URL
import com.example.coino.feature_coins.data.repositories.remote.CoinGeckoApi
import com.example.coino.feature_search.data.repositories.remote.SearchCoinApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {


    @Singleton
    @Provides
    fun providesCoinApi(): CoinGeckoApi {
        return Retrofit.Builder().baseUrl(BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create())
            .build()
            .create(CoinGeckoApi::class.java)
    }

    @Singleton
    @Provides
    fun provideSearchCoinApi(): SearchCoinApi {
        return Retrofit.Builder().baseUrl(BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create())
            .build()
            .create(SearchCoinApi::class.java)
    }

}