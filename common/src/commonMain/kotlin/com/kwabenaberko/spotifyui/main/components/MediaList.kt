package com.kwabenaberko.spotifyui.main.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp
import com.kwabenaberko.spotifyui.core.model.Media
import com.kwabenaberko.spotifyui.core.model.id

@Composable
fun MediaList(
    mediaList: List<Media>,
    onMediaClicked: (Media) -> Unit
) {
    LazyColumn(verticalArrangement = Arrangement.spacedBy(8.dp)) {
        items(mediaList, key = { it.id }) { media ->
            MediaItem(media = media) {
                onMediaClicked(media)
            }
        }
    }
}
