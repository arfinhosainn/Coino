package com.example.coino.feature_search.presentation


import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.coino.feature_search.presentation.components.SearchItem
import com.example.coino.feature_search.presentation.components.SearchToolbar

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun SearchScreen(
    searchCoinsViewModel: SearchCoinsViewModel = hiltViewModel()
) {

    val searchResultUiState by searchCoinsViewModel.searchResultUiState.collectAsState()
    val searchQuery by searchCoinsViewModel.searchQuery.collectAsState()

    Column(modifier = Modifier.fillMaxSize()) {
        SearchToolbar(
            onBackClick = { },
            searchQuery = searchQuery,
            onSearchQueryChanged = searchCoinsViewModel::onSearchQueryChanged,
            onSearchTriggered = {
                searchCoinsViewModel.searchResult(it)
            }
        )
        LazyColumn {
            items(searchResultUiState.coins) { coin ->
                SearchItem(searchCoins = coin)
            }
        }

    }

}
