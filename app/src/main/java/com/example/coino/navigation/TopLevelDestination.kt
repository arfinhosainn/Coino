package com.example.coino.navigation

import androidx.compose.ui.graphics.vector.ImageVector
import com.example.coino.designsystem.icons.CoinoIcons
import com.example.coino_cryptomarket.R

enum class TopLevelDestination(
    val selectedIcon: ImageVector,
    val unselectedIcon: ImageVector,
    val iconTextId: Int,
    val titleTextId: Int
) {
    HOME(
        selectedIcon = CoinoIcons.Upcoming,
        unselectedIcon = CoinoIcons.UpcomingBorder,
        iconTextId = R.string.home,
        titleTextId = R.string.app_name,
    ),
    MARKET(
        selectedIcon = CoinoIcons.Bookmarks,
        unselectedIcon = CoinoIcons.BookmarksBorder,
        iconTextId = R.string.market,
        titleTextId = R.string.app_name,
    ),
    ACCOUNT(
        selectedIcon = CoinoIcons.Grid3x3,
        unselectedIcon = CoinoIcons.Grid3x3,
        iconTextId = R.string.account,
        titleTextId = R.string.app_name,
    ),
}