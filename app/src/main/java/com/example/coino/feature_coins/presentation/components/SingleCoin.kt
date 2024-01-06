package com.example.coino.feature_coins.presentation.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.coino.feature_coins.domain.model.Coins


@Composable
fun SingleCoin(
    coins: Coins,
    onItemClick: (Coins) -> Unit
) {
    Surface(onClick = {onItemClick(coins)}, shape = MaterialTheme.shapes.large) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            AsyncImage(

                model = ImageRequest.Builder(LocalContext.current)
                    .data(coins.image).crossfade(true)
                    .build(),
                modifier = Modifier
                    .size(45.dp)
                    .clip(CircleShape),
                contentDescription = null
            )
            Column(horizontalAlignment = Alignment.Start) {
                Text(text = coins.name, maxLines = 1, overflow = TextOverflow.Ellipsis)
                Spacer(Modifier.height(4.dp))
                Text(
                    text = "${coins.total_volume}",
                    style = MaterialTheme.typography.bodyMedium,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
            }
            Column(modifier = Modifier.fillMaxWidth(), horizontalAlignment = Alignment.End) {
                Text(
                    text = coins.name, maxLines = 1, overflow = TextOverflow.Ellipsis
                )
                Spacer(Modifier.height(4.dp))
                Text(
                    text = "${coins.total_volume}",
                    style = MaterialTheme.typography.bodyMedium,
                    maxLines = 1
                )
            }
        }
    }
}

//@Preview(showBackground = true)
//@Composable
//fun PreviewSingleCoin() {
//    SingleCoin(
//        coins = Coins(
//            current_price = 100.22,
//            id = "bitcoin",
//            image = "https://assets.coingecko.com/coins/images/1/large/bitcoin.png?1696501400",
//            market_cap = 668117151942,
//            name = "Bitcoin",
//            price_change_24h = 2422.43,
//            price_change_percentage_24h = 433.43,
//            symbol = "btc",
//            total_supply = 4343.53,
//            total_volume = 3535L
//
//        )
//    )
//
//}