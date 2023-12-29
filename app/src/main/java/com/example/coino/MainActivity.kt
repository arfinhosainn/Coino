package com.example.coino

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.compose.collectAsLazyPagingItems
import com.example.coino.feature_coins.presentation.NavGraphs
import com.example.coino.feature_coins.presentation.coins_screen.CoinListViewModel
import com.example.coino.feature_coins.presentation.coins_screen.HomeScreen
import com.example.coino.feature_coins.presentation.destinations.HomeScreenDestination
import com.example.coino.presentation.theme.CoinoCryptoMarketTheme
import com.ramcosta.composedestinations.DestinationsNavHost
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CoinoCryptoMarketTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {

                    DestinationsNavHost(navGraph = NavGraphs.root)

                }
            }
        }
    }
}