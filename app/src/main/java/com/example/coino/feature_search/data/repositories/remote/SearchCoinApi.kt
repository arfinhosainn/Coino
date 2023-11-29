package com.example.coino.feature_search.data.repositories.remote

import com.example.coino.feature_search.data.repositories.remote.DTOs.SearchCoinDtos
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface SearchCoinApi {

    @GET("search")
    suspend fun searchCoins(
        @Query("query") query: String
    ): SearchCoinDtos

}