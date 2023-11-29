package com.example.coino.di

import android.content.Context
import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.room.Room
import com.example.coino.feature_coins.data.repositories.CoinsRepositoryImpl
import com.example.coino.feature_coins.data.repositories.coinsRepositoryImpl.CoinsRemoteMediator
import com.example.coino.feature_coins.data.repositories.local.CoinsDatabase
import com.example.coino.feature_coins.data.repositories.local.CoinsEntity
import com.example.coino.feature_coins.data.repositories.remote.CoinGeckoApi
import com.example.coino.feature_coins.domain.CoinsRepository
import com.example.coino.feature_search.data.repositories.SearchCoinsRepositoryImpl
import com.example.coino.feature_search.data.repositories.remote.SearchCoinApi
import com.example.coino.feature_search.domain.SearchCoinsRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@OptIn(ExperimentalPagingApi::class)
@Module
@InstallIn(SingletonComponent::class)
object CoinRepositoryModule {

    @Singleton
    @Provides
    fun providesCoinListRepository(api: CoinGeckoApi): CoinsRepository {
        return CoinsRepositoryImpl(api)
    }

    @Singleton
    @Provides
    fun ProvidesCoinSearchRepository(api: SearchCoinApi): SearchCoinsRepository {
        return SearchCoinsRepositoryImpl(api)
    }

    @Provides
    @Singleton
    fun providesCoinsDatabase(@ApplicationContext context: Context): CoinsDatabase {
        return Room.databaseBuilder(
            context,
            CoinsDatabase::class.java, "coins.db"
        ).build()
    }

    @Singleton
    @Provides
    fun providesCoinsPager(
        coinsDb: CoinsDatabase,
        coinsApi: CoinGeckoApi
    ): Pager<Int, CoinsEntity> {
        return Pager(
            config = PagingConfig(pageSize = 20),
            remoteMediator = CoinsRemoteMediator(
                coinsDb = coinsDb,
                coinsApi = coinsApi
            ),
            pagingSourceFactory = {
                coinsDb.dao.pagingSource()
            }
        )
    }


}