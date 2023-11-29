package com.example.coino.di

import androidx.paging.ExperimentalPagingApi
import com.example.coino.feature_search.data.repositories.SearchCoinsRepositoryImpl
import com.example.coino.feature_search.data.repositories.remote.SearchCoinApi
import com.example.coino.feature_search.domain.SearchCoinsRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@OptIn(ExperimentalPagingApi::class)
@Module
@InstallIn(SingletonComponent::class)
object CoinRepositoryModule {

    @Singleton
    @Provides
    fun ProvidesCoinSearchRepository(api: SearchCoinApi): SearchCoinsRepository {
        return SearchCoinsRepositoryImpl(api)
    }


}