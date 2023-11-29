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
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import com.example.coino.feature_coins.domain.model.Coins
import com.example.coino.feature_coins.presentation.components.SingleCoin

@Composable
fun HomeScreen(
    coins: LazyPagingItems<Coins>
) {

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
                ) { index: Int ->
                    val coinList: Coins? = coins[index]
                    if (coinList != null) {
                        SingleCoin(coins = coinList)
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

@Composable
fun CoinGainersContent(

) {



}