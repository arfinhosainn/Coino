package com.example.coino.feature_coins.presentation.coins_screen

import com.example.coino.feature_coins.domain.model.Coins

data class CoinListUiState(
    val isLoading: Boolean = false,
    val coins: List<Coins> = emptyList(),
    val error: String = ""
)