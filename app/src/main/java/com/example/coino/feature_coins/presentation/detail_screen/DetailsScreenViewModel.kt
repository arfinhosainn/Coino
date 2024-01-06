package com.example.coino.feature_coins.presentation.detail_screen

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.coino.core.util.Resource
import com.example.coino.feature_coins.domain.CoinsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class DetailsScreenViewModel @Inject constructor(
    private val repository: CoinsRepository,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _state = mutableStateOf(PriceState())
    val state: State<PriceState> = _state


    init {
        savedStateHandle.get<String>("id")?.let { coindId ->
            getPriceStats(coindId)
            Log.d("prices", "$coindId ")
        }

    }


    private fun getPriceStats(id: String) {
        repository.getCoinPrices(
            id = id, currency = "usd", days = 1
        ).onEach { result ->
            when (result) {
                is Resource.Success -> {
                    _state.value = PriceState(price = result.data)
                }

                is Resource.Error -> {
                    _state.value = PriceState(error = "This is  wrong")
                }

                is Resource.Loading -> {
                    _state.value = PriceState(isLoading = true)
                }
            }
        }.launchIn(viewModelScope)
    }


}