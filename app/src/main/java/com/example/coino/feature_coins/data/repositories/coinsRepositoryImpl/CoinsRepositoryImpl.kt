package com.example.coino.feature_coins.data.repositories

import android.util.Log
import com.example.coino.core.util.Resource
import com.example.coino.feature_coins.data.repositories.remote.CoinGeckoApi
import com.example.coino.feature_coins.data.repositories.remote.DTOs.toCoinPrice
import com.example.coino.feature_coins.data.repositories.remote.DTOs.toCoins
import com.example.coino.feature_coins.domain.CoinsRepository
import com.example.coino.feature_coins.domain.model.CoinPrices
import com.example.coino.feature_coins.domain.model.Coins
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import okio.IOException
import retrofit2.HttpException
import javax.inject.Inject

class CoinsRepositoryImpl @Inject constructor(
    private val api: CoinGeckoApi
) : CoinsRepository {
    override fun getCoins(
        currency: String,
        priceChangePercentage: String,
        itemOrder: String,
        itemPerPage: Int,
        page: Int
    ): Flow<Resource<List<Coins>>> = flow {
        try {
            emit(Resource.Loading())
            val coinsResult = api.getCoins(
                currency = currency,
                priceChangePercentage = priceChangePercentage,
                itemOrder = itemOrder,
                page = page
            ).map {
                it.toCoins()
            }
            emit(Resource.Success(coinsResult))
            Log.d("coinResult", "getCoins: $coinsResult")
        } catch (e: HttpException) {
            emit(Resource.Error("An unexpected error occurred"))
        } catch (e: IOException) {
            emit(Resource.Error("Couldn't reach server. Check your internet connection."))
        }
    }

    override fun getCoinPrices(id: String, currency: String, days: Int): Flow<Resource<CoinPrices>> =
        flow {
            try {
                emit(Resource.Loading())
                val coinPrices = api.getCoinPrices(
                    id = id, currency = currency, days = days
                ).toCoinPrice()
                emit(Resource.Success(coinPrices))
                Log.d("prices", "getCoinPrices: $coinPrices")
            } catch (e: HttpException) {
                emit(Resource.Error("An unexpected error occurred"))
            } catch (e: IOException) {
                emit(Resource.Error("Couldn't reach server. Check your internet connection."))
            }

        }
}