package com.kwabenaberko.spotifyui.core.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import com.kwabenaberko.spotifyui.core.model.Artist
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.painterResource

@OptIn(ExperimentalResourceApi::class)
@Composable
fun PlaylistImage(
    image: DrawableResource?,
    size: DpSize,
    modifier: Modifier = Modifier,
) {
    Box(
        modifier = Modifier
            .size(size)
            .background(Color.Black)
            .aspectRatio(1f)
    ) {
        image?.let { image ->
            Image(
                painter = painterResource(image),
                contentDescription = null,
                modifier = modifier.width(size.width),
                contentScale = ContentScale.Crop
            )
        }
    }
}