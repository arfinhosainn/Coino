package com.example.coino.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.paging.compose.collectAsLazyPagingItems
import com.example.coino.core.util.Screen
import com.example.coino.feature_coins.presentation.coins_screen.CoinListViewModel
import com.example.coino.feature_coins.presentation.coins_screen.HomeScreen
import com.example.coino.feature_coins.presentation.components.RealLineChart

