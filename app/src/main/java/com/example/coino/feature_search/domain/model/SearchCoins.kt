package com.example.coino.feature_search.domain.model

import androidx.compose.runtime.Immutable


@Immutable
data class SearchCoins(
    val api_symbol: String,
    val id: String,
    val name: String,
    val symbol: String,
    val large: String,
    val isFavorite: Boolean
)

