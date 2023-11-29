package com.example.coino.feature_coins.presentation.coins_screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.cachedIn
import androidx.paging.map
import com.example.coino.feature_coins.data.repositories.local.CoinsEntity
import com.example.coino.feature_coins.data.repositories.remote.DTOs.toCoins
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.map
import javax.inject.Inject

@HiltViewModel
class CoinListViewModel @Inject constructor(
    pager: Pager<Int, CoinsEntity>
) : ViewModel() {

    val coinsPagingFlow = pager
        .flow
        .map { pagingData ->
            pagingData.map { it.toCoins() }
        }
        .cachedIn(viewModelScope)
}