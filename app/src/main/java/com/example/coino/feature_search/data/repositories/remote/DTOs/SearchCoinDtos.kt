package com.example.coino.feature_search.data.repositories.remote.DTOs

data class SearchCoinDtos(
    val categories: List<Any>,
    val coins: List<CoinDtos>,
    val icos: List<Any>,
    val nfts: List<Any>
)