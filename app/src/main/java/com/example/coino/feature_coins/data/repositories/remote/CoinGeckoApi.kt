package com.example.coino.feature_coins.data.repositories.remote

import com.example.coino.feature_coins.data.repositories.remote.DTOs.CoinsItemDto
import retrofit2.http.GET
import retrofit2.http.Query

interface CoinGeckoApi {

    @GET("coins/markets")
    suspend fun getCoins(
        @Query("vs_currency") currency: String,
        @Query("price_change_percentage") priceChangePercentage: String,
        @Query("order") itemOrder: String,
        @Query("page") page: Int
    ): List<CoinsItemDto>


}