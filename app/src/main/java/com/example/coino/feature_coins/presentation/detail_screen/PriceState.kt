package com.example.coino.feature_coins.presentation.detail_screen

import com.example.coino.feature_coins.domain.model.CoinPrices


data class PriceState(
    val price: CoinPrices? = null,
    val isLoading: Boolean = false,
    val error: String? = null
)