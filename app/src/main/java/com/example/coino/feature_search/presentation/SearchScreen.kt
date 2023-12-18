package com.example.coino.feature_search.presentation


import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
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

        Box(modifier = Modifier.fillMaxSize()) {
            if (searchResultUiState.isLoading) {
                CircularProgressIndicator(
                    modifier = Modifier.align(Alignment.Center)
                )
            } else if (searchQuery.isEmpty()) {
                Text(
                    text = "Search Coins",
                    modifier = Modifier.align(Alignment.Center)
                )
            } else if (searchResultUiState.coins.isEmpty()) {
                Text(
                    text = "No Coin Found",
                    modifier = Modifier.align(Alignment.Center)
                )

            } else {
                LazyColumn {
                    items(searchResultUiState.coins) { coin ->
                        SearchItem(
                            searchCoins = coin,
                            onFavoriteSwitched = { searchCoinsViewModel.onFavoriteSwitch(TODO()) }
                        )
                    }
                }
            }
        }
    }
}
