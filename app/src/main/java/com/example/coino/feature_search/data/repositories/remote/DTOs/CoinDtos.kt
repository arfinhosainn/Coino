package com.example.coino.feature_search.data.repositories.remote.DTOs

import com.example.coino.feature_search.domain.model.SearchCoins

data class CoinDtos(
    val api_symbol: String,
    val id: String,
    val large: String,
    val market_cap_rank: Int?,
    val name: String,
    val symbol: String,
    val thumb: String,
    val is_favorite: Boolean
)

fun CoinDtos.toSearchCoins(): SearchCoins {
    return SearchCoins(
        api_symbol = api_symbol,
        id = id,
        name = name,
        symbol = symbol,
        large = large,
        isFavorite = is_favorite
    )
}