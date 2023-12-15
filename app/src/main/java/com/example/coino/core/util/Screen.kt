package com.example.coino.core.util

sealed class Screen (val route: String){
    data object CoinListScreen: Screen("coin_list_screen")
    data object CoinDetailsScreen: Screen("coin_detail_screen")
}