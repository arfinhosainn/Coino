package com.example.coino.feature_search.presentation

import com.example.coino.feature_search.domain.model.SearchCoins

data class SearchResultUiState(
    val isLoading: Boolean = false,
    val coins: List<SearchCoins> = emptyList(),
    val error: String = ""
)