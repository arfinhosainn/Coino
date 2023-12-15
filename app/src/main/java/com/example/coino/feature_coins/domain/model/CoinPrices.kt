package com.example.coino.feature_coins.domain.model

import androidx.compose.runtime.Immutable

@Immutable
data class CoinPrices(
    val prices: List<List<Double>>,
)