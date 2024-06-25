package com.kwabenaberko.spotifyui.core.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import com.kwabenaberko.spotifyui.core.model.Podcast
import com.kwabenaberko.spotifyui.core.model.PodcastArtwork
import com.kwabenaberko.spotifyui.fromHex
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.painterResource

@OptIn(ExperimentalResourceApi::class)
@Composable
fun PodcastImage(
    artwork: PodcastArtwork,
    artworkTitle: String? = null,
    size: DpSize,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .size(size)
            .clip(RoundedCornerShape(12.dp))
            .background(Color.Black)
            .aspectRatio(1f)
    ) {
        when (artwork) {
            is PodcastArtwork.Color -> {
                Box(
                    modifier = modifier
                        .fillMaxSize()
                        .background(Color.fromHex(artwork.hexColor)),
                    contentAlignment = Alignment.Center
                ) {
                    artworkTitle?.let { title ->
                        PodcastTitleText(
                            title = title,
                            modifier = Modifier.padding(8.dp)
                        )
                    }
                }
            }

            is PodcastArtwork.Image -> {
                Image(
                    painter = painterResource(artwork.image),
                    contentDescription = null,
                    modifier = modifier.width(size.width),
                    contentScale = ContentScale.Crop
                )
            }
        }
    }
}

