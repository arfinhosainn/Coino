package com.example.coino.feature_coins.presentation.coins_screen

import android.widget.Toast
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import com.example.coino.feature_coins.domain.model.Coins
import com.example.coino.feature_coins.presentation.components.SingleCoin
import com.example.coino.feature_coins.presentation.destinations.LineChartsDestination
import com.example.coino.feature_coins.presentation.destinations.RealLineChartDestination
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.annotation.RootNavGraph
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

@RootNavGraph(start = true)
@Destination
@Composable
fun HomeScreen(
    navigator: DestinationsNavigator
) {

    val viewModel: CoinListViewModel = hiltViewModel()
    val coin = viewModel.coinsPagingFlow.collectAsLazyPagingItems()

    val coins : LazyPagingItems<Coins> = coin

    val context = LocalContext.current
    LaunchedEffect(key1 = coins.loadState) {
        if (coins.loadState.refresh is LoadState.Error) {
            Toast.makeText(
                context,
                "Error:" +
                        (coins.loadState.refresh as LoadState.Error).error.message,
                Toast.LENGTH_LONG
            ).show()
        }
    }

    Box(modifier = Modifier.fillMaxSize()) {
        if (coins.loadState.refresh is LoadState.Loading) {
            CircularProgressIndicator(
                modifier = Modifier.align(Alignment.Center)
            )
        } else {
            LazyColumn(modifier = Modifier.fillMaxSize()) {
                items(
                    count = coins.itemCount,
                    key = {
                        it
                    }
                ) { index: Int ->
                    val coinList: Coins? = coins[index]
                    if (coinList != null) {
                        SingleCoin(coins = coinList,
                            onItemClick = {
                                navigator.navigate(RealLineChartDestination())
                            })
                    }
                }
                item {
                    if (coins.loadState.append is LoadState.Loading) {
                        CircularProgressIndicator()
                    }
                }
            }
        }
    }
}