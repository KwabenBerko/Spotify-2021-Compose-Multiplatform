package com.kwabenaberko.spotifyui.main.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import com.kwabenaberko.spotifyui.core.components.AlbumImage
import com.kwabenaberko.spotifyui.core.components.ArtistImage
import com.kwabenaberko.spotifyui.core.components.PlaylistImage
import com.kwabenaberko.spotifyui.core.components.PodcastImage
import com.kwabenaberko.spotifyui.core.components.TrackImage
import com.kwabenaberko.spotifyui.core.model.Album
import com.kwabenaberko.spotifyui.core.model.Artist
import com.kwabenaberko.spotifyui.core.model.Media
import com.kwabenaberko.spotifyui.core.model.Playlist
import com.kwabenaberko.spotifyui.core.model.Podcast
import com.kwabenaberko.spotifyui.core.model.Track
import com.kwabenaberko.spotifyui.core.model.isEditorsPick
import com.kwabenaberko.spotifyui.fromHex
import org.jetbrains.compose.resources.ExperimentalResourceApi

@Composable
fun MediaItem(
    media: Media,
    modifier: Modifier = Modifier,
    onClicked: () -> Unit
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .background(MaterialTheme.colorScheme.background)
            .padding(4.dp)
            .clickable(onClick = onClicked)
    ) {
        Image(media)
        Spacer(Modifier.width(12.dp))
        Column {
            Title(media)
            Spacer(Modifier.height(4.dp))
            Info(
                media = media,
                isEditorsPick = media.isEditorsPick
            )
        }
    }
}

@OptIn(ExperimentalResourceApi::class)
@Composable
private fun Image(
    media: Media,
    modifier: Modifier = Modifier
) {
    val size = remember { DpSize(width = 50.dp, height = 50.dp) }
    Box(modifier = modifier) {
        when (media) {
            is Track -> TrackImage(image = media.image, size = size)
            is Artist -> ArtistImage(image = media.image, size = size)
            is Album -> AlbumImage(image = media.image, size = size)
            is Playlist -> PlaylistImage(image = media.image, size = size)
            is Podcast -> PodcastImage(artwork = media.artwork, size = size)
        }
    }
}

@Composable
private fun Title(
    media: Media,
    modifier: Modifier = Modifier
) {
    val title = remember(media) {
        when (media) {
            is Track -> media.title
            is Artist -> media.name
            is Album -> media.title
            is Playlist -> media.title ?: ""
            is Podcast -> media.title
        }
    }

    Text(
        text = title,
        modifier = modifier,
        style = MaterialTheme.typography.titleMedium,
        fontWeight = FontWeight.Medium,
        color = Color.White,
        maxLines = 1
    )
}

@Composable
private fun Info(
    media: Media,
    isEditorsPick: Boolean,
    modifier: Modifier = Modifier
) {
    val title = remember(media) {
        when (media) {
            is Track -> "Song • ${media.artist.name}"
            is Artist -> "Artist"
            is Album -> "Album • ${media.artist.name}"
            is Playlist -> "Playlist"
            is Podcast -> "Podcast"
        }
    }

    Row {
        if (isEditorsPick) {
            EditorsPickIcon()
            Spacer(Modifier.width(5.dp))
        }
        Text(
            text = title,
            modifier = modifier,
            style = MaterialTheme.typography.titleSmall,
            fontWeight = FontWeight.Medium,
            color = Color.fromHex(hex = "#B3B3B3"),
        )
    }
}

@Composable
private fun EditorsPickIcon(modifier: Modifier = Modifier) {
    Text(
        text = "E",
        modifier = modifier
            .clip(RoundedCornerShape(3.dp))
            .background(Color.fromHex(hex = "#C4C4C4"))
            .padding(horizontal = 5.dp, vertical = 2.dp),
        style = MaterialTheme.typography.labelSmall,
        fontWeight = FontWeight.Bold,
        color = Color.Black
    )
}
