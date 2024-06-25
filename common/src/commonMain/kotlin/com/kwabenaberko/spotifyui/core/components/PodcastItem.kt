package com.kwabenaberko.spotifyui.core.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import com.kwabenaberko.spotifyui.core.model.Podcast

@Composable
fun PodcastItem(
    podcast: Podcast,
    modifier: Modifier = Modifier,
    size: DpSize = DpSize(width = 100.dp, height = 100.dp),
    onClicked: () -> Unit
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
            .width(size.width)
            .clickable(onClick = onClicked)
    ) {

        SelectionOverlay(podcast.isSelected) {
            PodcastImage(
                artwork = podcast.artwork,
                artworkTitle = podcast.title,
                size = size
            )
        }

        Spacer(Modifier.height(10.dp))

        PodcastTitleText(
            title = podcast.title,
            modifier = Modifier.alpha(if (podcast.artwork.isImage) 1.0f else 0.0f)
        )
    }
}
