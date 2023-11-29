package com.example.coino.feature_coins.data.repositories.coinsRepositoryImpl

import android.util.Log
import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import androidx.room.withTransaction
import co.yml.charts.common.extensions.isNotNull
import com.example.coino.core.util.COINS_ORDER
import com.example.coino.core.util.COINS_PRICE_CHANGE_IN_HOUR
import com.example.coino.core.util.CURRENCY
import com.example.coino.feature_coins.data.repositories.local.CoinsDatabase
import com.example.coino.feature_coins.data.repositories.local.CoinsEntity
import com.example.coino.feature_coins.data.repositories.remote.CoinGeckoApi
import com.example.coino.feature_coins.data.repositories.remote.DTOs.toCoinsEntity
import okio.IOException
import retrofit2.HttpException

@OptIn(ExperimentalPagingApi::class)
class CoinsRemoteMediator(
    private val coinsDb: CoinsDatabase,
    private val coinsApi: CoinGeckoApi
) : RemoteMediator<Int, CoinsEntity>() {

    private var currentPage = 1

    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, CoinsEntity>
    ): MediatorResult {
        return try {
            val loadKey = when (loadType) {
                LoadType.REFRESH -> {
                    currentPage = 1
                    currentPage
                }

                LoadType.PREPEND -> return MediatorResult.Success(
                    endOfPaginationReached = true
                )

                LoadType.APPEND -> {
                    val lastItem = state.lastItemOrNull()
                    if (lastItem.isNotNull()){
                        currentPage++
                    }
                    currentPage

                }
            }
            val coins = coinsApi.getCoins(
                currency = CURRENCY,
                priceChangePercentage = COINS_PRICE_CHANGE_IN_HOUR,
                itemOrder = COINS_ORDER,
                page = loadKey
            )
            Log.d("loadkey", "load: $loadKey")
            coinsDb.withTransaction {
                if (loadType == LoadType.REFRESH) {
                    coinsDb.dao.clearAll()
                }
                val coinsEntities = coins.map { it.toCoinsEntity() }
                coinsDb.dao.upsertAll(coinsEntities)
            }
            MediatorResult.Success(
                endOfPaginationReached = coins.isEmpty()
            )


        } catch (e: IOException) {
            MediatorResult.Error(e)
        } catch (e: HttpException) {
            MediatorResult.Error(e)
        }


    }
}