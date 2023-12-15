package com.example.coino.feature_coins.data.repositories.remote.DTOs

import com.example.coino.feature_coins.domain.model.CoinPrices

data class CoinPriceDto(
    val market_caps: List<List<Double>>,
    val prices: List<List<Double>>,
    val total_volumes: List<List<Double>>
)

fun CoinPriceDto.toCoinPrice(): CoinPrices {
    return CoinPrices(
        prices = prices
    )
}