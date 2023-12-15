package com.example.coino.feature_coins.domain

import com.example.coino.core.util.Resource
import com.example.coino.feature_coins.domain.model.CoinPrices
import com.example.coino.feature_coins.domain.model.Coins
import kotlinx.coroutines.flow.Flow
import retrofit2.http.Query

interface CoinsRepository {

    fun getCoins(
        currency: String,
        priceChangePercentage: String,
        itemOrder: String,
        itemPerPage: Int,
        page: Int
    ): Flow<Resource<List<Coins>>>

    fun getCoinPrices(
        @Query("vs_currency") currency: String,
        @Query("days") days: Int
    ): Flow<Resource<CoinPrices>>


}