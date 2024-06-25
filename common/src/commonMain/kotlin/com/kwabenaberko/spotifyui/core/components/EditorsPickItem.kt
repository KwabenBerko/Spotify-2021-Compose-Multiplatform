package com.kwabenaberko.spotifyui.core.components

import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.DpSize
import com.kwabenaberko.spotifyui.core.model.Album
import com.kwabenaberko.spotifyui.core.model.Artist
import com.kwabenaberko.spotifyui.core.model.EditorsPick
import com.kwabenaberko.spotifyui.core.model.Playlist
import com.kwabenaberko.spotifyui.core.model.Podcast
import com.kwabenaberko.spotifyui.core.model.Track

@Composable
fun EditorsPickItem(
    pick: EditorsPick,
    size: DpSize,
    onClicked: () -> Unit
) {
    when (pick.media) {
        is Artist -> {
            ArtistItem(artist = pick.media, size = size, onClicked = onClicked)
        }

        is Track -> {
            TrackItem(track = pick.media, size = size, onClicked = onClicked)
        }

        is Album -> {
            AlbumItem(album = pick.media, size = size, onClicked = onClicked)
        }

        is Playlist -> {
            PlaylistItem(playlist = pick.media, size = size, onClicked = onClicked)
        }

        is Podcast -> {
            PodcastItem(podcast = pick.media, size = size, onClicked = onClicked)
        }
    }
}
