package com.example.coino.feature_coins.data.repositories.remote.DTOs

import com.example.coino.feature_coins.data.repositories.local.CoinsEntity
import com.example.coino.feature_coins.domain.model.Coins

data class CoinsItemDto(
    val ath: Double,
    val ath_change_percentage: Double,
    val ath_date: String,
    val atl: Double,
    val atl_change_percentage: Double,
    val atl_date: String,
    val circulating_supply: Double,
    val current_price: Double,
    val fully_diluted_valuation: Long?,
    val high_24h: Double,
    val id: String,
    val image: String,
    val last_updated: String,
    val low_24h: Double,
    val market_cap: Long,
    val market_cap_change_24h: Double,
    val market_cap_change_percentage_24h: Double,
    val market_cap_rank: Int,
    val name: String,
    val price_change_24h: Double,
    val price_change_percentage_24h: Double,
    val roi: Roi,
    val symbol: String,
    val total_supply: Double?,
    val total_volume: Double
)

fun CoinsItemDto.toCoins(): Coins {
    return Coins(
        current_price = current_price,
        id = id,
        image = image,
        market_cap = market_cap,
        name = name,
        price_change_24h = price_change_24h,
        price_change_percentage_24h = price_change_percentage_24h,
        symbol = symbol,
        total_supply = total_supply,
        total_volume = total_volume
    )
}


fun CoinsItemDto.toCoinsEntity(): CoinsEntity {
    return CoinsEntity(
        current_price = current_price,
        id = id,
        image = image,
        market_cap = market_cap,
        name = name,
        price_change_24h = price_change_24h,
        price_change_percentage_24h = price_change_percentage_24h,
        symbol = symbol,
        total_supply = total_supply,
        total_volume = total_volume
    )
}

fun CoinsEntity.toCoins(): Coins {
    return Coins(
        current_price = current_price,
        id = id,
        image = image,
        market_cap = market_cap,
        name = name,
        price_change_24h = price_change_24h,
        price_change_percentage_24h = price_change_percentage_24h,
        symbol = symbol,
        total_supply = total_supply,
        total_volume = total_volume
    )
}