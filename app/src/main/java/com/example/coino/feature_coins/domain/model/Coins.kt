package com.example.coino.feature_coins.domain.model

import androidx.compose.runtime.Immutable

@Immutable
data class Coins(
    val current_price: Double,
    val id: String,
    val image: String,
    val market_cap: Long,
    val name: String,
    val price_change_24h: Double,
    val price_change_percentage_24h: Double,
    val symbol: String,
    val total_supply: Double?,
    val total_volume: Double
)
