package com.example.coino.feature_search.presentation.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Star
import androidx.compose.material.icons.rounded.StarBorder
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.coino.feature_search.domain.model.SearchCoins
import com.example.coino_cryptomarket.R

@Composable
fun SearchItem(
    searchCoins: SearchCoins,
    onFavoriteSwitched: (Boolean) -> Unit,
) {
    Surface(shape = MaterialTheme.shapes.large) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            AsyncImage(
                model = searchCoins.large,
                modifier = Modifier
                    .size(58.dp)
                    .clip(CircleShape),
                contentScale = ContentScale.Crop,
                contentDescription = "Image for ${searchCoins.name}"
            )
            Spacer(Modifier.width(16.dp))
            Column(
                modifier = Modifier.weight(1f),
                horizontalAlignment = Alignment.Start
            ) {
                Text(
                    text = searchCoins.name,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
                Spacer(Modifier.height(4.dp))
                Text(
                    text = searchCoins.symbol,
                    style = MaterialTheme.typography.bodyMedium,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
            }
            Spacer(Modifier.width(16.dp))
            Icon(
                imageVector = if (searchCoins.isFavorite) {
                    Icons.Rounded.Star
                } else {
                    Icons.Rounded.StarBorder
                },
                contentDescription = stringResource(
                    id = R.string.home,
                ),
                tint = if (searchCoins.isFavorite) {
                    Color.Yellow
                } else {
                    Color.Black
                },
                modifier = Modifier.size(36.dp).clickable {
                    onFavoriteSwitched(searchCoins.isFavorite)
                }
            )
        }
    }
}