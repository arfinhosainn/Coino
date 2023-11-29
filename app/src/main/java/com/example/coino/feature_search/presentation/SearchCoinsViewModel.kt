package com.example.coino.feature_search.presentation

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.coino.core.util.Resource
import com.example.coino.feature_search.domain.SearchCoinsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchCoinsViewModel @Inject constructor(
    private val searchRepository: SearchCoinsRepository,
    private val savedStateHandle: SavedStateHandle
) : ViewModel() {
    val searchQuery = savedStateHandle.getStateFlow(key = SEARCH_QUERY, initialValue = "")

    val searchResultUiState = MutableStateFlow(SearchResultUiState())


    fun searchResult(query: String) {
        viewModelScope.launch {
            searchRepository.searchCoins(query = query).collectLatest { result ->
                when (result) {
                    is Resource.Success -> {
                        searchResultUiState.value = SearchResultUiState(
                            coins = result.data ?: emptyList()
                        )
                    }

                    is Resource.Loading -> {
                        searchResultUiState.value = SearchResultUiState(
                            isLoading = true
                        )

                    }

                    is Resource.Error -> {
                        searchResultUiState.value = SearchResultUiState(
                            error = result.message ?: "There's something wrong"
                        )
                    }
                }

            }
        }
    }

    fun onSearchQueryChanged(query: String) {
        savedStateHandle[SEARCH_QUERY] = query
    }

}


private const val SEARCH_QUERY = "searchQuery"