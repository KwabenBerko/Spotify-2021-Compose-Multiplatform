package com.kwabenaberko.spotifyui.core.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import com.kwabenaberko.spotifyui.core.model.Artist
import org.jetbrains.compose.resources.ExperimentalResourceApi


@OptIn(ExperimentalResourceApi::class)
@Composable
fun ArtistItem(
    artist: Artist,
    modifier: Modifier = Modifier,
    size: DpSize = DpSize(width = 110.dp, height = 110.dp),
    onClicked: () -> Unit
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
            .width(size.width)
            .clickable(onClick = onClicked)
    ) {
        SelectionOverlay(artist.isSelected) {
            ArtistImage(
                image = artist.image,
                size = size
            )
        }

        Spacer(Modifier.height(12.dp))

        ArtistName(artist.name)
    }
}

@Composable
private fun ArtistName(
    name: String,
    modifier: Modifier = Modifier
) {
    Text(
        text = name,
        modifier = modifier,
        style = MaterialTheme.typography.labelMedium,
        fontWeight = FontWeight.Bold,
        color = Color.White,
        textAlign = TextAlign.Center,
        overflow = TextOverflow.Ellipsis,
        minLines = 2,
        maxLines = 2
    )
}
