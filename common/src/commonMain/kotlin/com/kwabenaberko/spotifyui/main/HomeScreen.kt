package com.kwabenaberko.spotifyui.main

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import com.kwabenaberko.spotifyui.core.components.AlbumItem
import com.kwabenaberko.spotifyui.core.components.ArtistItem
import com.kwabenaberko.spotifyui.core.components.EditorsPickItem
import com.kwabenaberko.spotifyui.core.components.PlaylistItem
import com.kwabenaberko.spotifyui.core.components.PodcastItem
import com.kwabenaberko.spotifyui.core.components.TrackItem
import com.kwabenaberko.spotifyui.core.model.Album
import com.kwabenaberko.spotifyui.core.model.Artist
import com.kwabenaberko.spotifyui.core.model.EditorsPick
import com.kwabenaberko.spotifyui.core.model.Media
import com.kwabenaberko.spotifyui.core.model.Playlist
import com.kwabenaberko.spotifyui.core.model.Podcast
import com.kwabenaberko.spotifyui.core.model.Track
import com.kwabenaberko.spotifyui.fromHex
import com.kwabenaberko.spotifyui.mockEditorsPicks
import com.kwabenaberko.spotifyui.mockHomeRecentlyPlayed
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource
import spotifyui.common.generated.resources.Res
import spotifyui.common.generated.resources.main_editors_picks
import spotifyui.common.generated.resources.main_top_artists
import spotifyui.common.generated.resources.main_top_songs
import spotifyui.common.generated.resources.main_year_in_review_review
import spotifyui.common.generated.resources.spotify_wrapped_artists
import spotifyui.common.generated.resources.spotify_wrapped_top_songs
import spotifyui.common.generated.resources.wrapped

data class HomeState(
    val recentlyPlayed: List<Media> = emptyList(),
    val editorsPicks: List<EditorsPick> = emptyList()
)

val mockHomeState = HomeState(
    recentlyPlayed = mockHomeRecentlyPlayed,
    editorsPicks = mockEditorsPicks
)

@Composable
fun HomeScreen() {
    val state by remember { mutableStateOf(mockHomeState) }
    HomeScreenContent(
        state = state
    )
}

@Composable
private fun HomeScreenContent(state: HomeState) {
    val scrollState = rememberScrollState()

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .verticalScroll(scrollState)
    ) {
        RecentlyPlayedList(state.recentlyPlayed)
        Spacer(Modifier.height(14.dp))
        Wrapped()
        Spacer(Modifier.height(18.dp))
        EditorsPicksList(state.editorsPicks)
        Spacer(Modifier.height(8.dp))
    }
}

@Composable
private fun RecentlyPlayedList(recentlyPlayed: List<Media>, modifier: Modifier = Modifier) {
    val scrollState = rememberScrollState()
    val size = remember { DpSize(width = 105.dp, height = 105.dp) }

    Row(
        modifier = modifier.horizontalScroll(scrollState).padding(horizontal = 16.dp),
        horizontalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        recentlyPlayed.forEach { media ->
            when (media) {
                is Track -> {
                    TrackItem(track = media, size = size, onClicked = {})
                }

                is Artist -> {
                    ArtistItem(artist = media, size = size, onClicked = {})
                }

                is Album -> {
                    AlbumItem(album = media, size = size, onClicked = {})
                }

                is Playlist -> {
                    PlaylistItem(playlist = media, size = size, onClicked = {})
                }

                is Podcast -> {
                    PodcastItem(podcast = media, size = size, onClicked = {})
                }
            }
        }
    }
}

@OptIn(ExperimentalResourceApi::class)
@Composable
private fun EditorsPicksList(
    editorsPicks: List<EditorsPick>,
    modifier: Modifier = Modifier
) {
    val size = remember { DpSize(width = 150.dp, height = 150.dp) }
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Text(
            text = stringResource(Res.string.main_editors_picks),
            modifier = Modifier.padding(start = 16.dp),
            style = MaterialTheme.typography.headlineSmall,
            color = Color.White,
            fontWeight = FontWeight.Bold,
        )
        LazyRow(
            contentPadding = PaddingValues(horizontal = 16.dp),
            horizontalArrangement = Arrangement.spacedBy(10.dp),
            verticalAlignment = Alignment.Top
        ) {
            items(editorsPicks, key = { it.id }) { pick ->
                EditorsPickItem(pick = pick, size = size, onClicked = {})
            }
        }
    }
}

@OptIn(ExperimentalResourceApi::class)
@Composable
private fun Wrapped(modifier: Modifier = Modifier) {
    Column(modifier = modifier.padding(horizontal = 16.dp)) {
        Row(
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .size(58.dp)
                    .background(Color.Black)
                    .clip(RoundedCornerShape(2.dp))
            ) {
                Image(
                    painter = painterResource(Res.drawable.wrapped),
                    contentDescription = null,
                    modifier = Modifier.fillMaxSize(),
                    contentScale = ContentScale.Fit
                )
            }

            Column {
                Text(
                    text = "#SPOTIFYWRAPPED",
                    style = MaterialTheme.typography.labelSmall,
                    color = Color.fromHex(hex = "#9C9C9C")
                )
                Text(
                    text = stringResource(Res.string.main_year_in_review_review),
                    style = MaterialTheme.typography.headlineSmall,
                    color = Color.White,
                    fontWeight = FontWeight.Bold
                )
            }
        }

        Spacer(Modifier.height(16.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            WrappedItem(
                title = stringResource(Res.string.main_top_songs),
                image = Res.drawable.spotify_wrapped_top_songs,
                onClicked = {}
            )
            WrappedItem(
                title = stringResource(Res.string.main_top_artists),
                image = Res.drawable.spotify_wrapped_artists,
                onClicked = {}
            )
        }
    }
}

@OptIn(ExperimentalResourceApi::class)
@Composable
private fun WrappedItem(
    title: String,
    image: DrawableResource,
    modifier: Modifier = Modifier,
    onClicked: () -> Unit
) {
    val size = remember { DpSize(width = 150.dp, height = 150.dp) }

    Column(modifier = modifier.width(size.width).clickable(onClick = onClicked)) {
        Box(modifier = Modifier.size(size).background(Color.Black)) {
            Image(
                painter = painterResource(image),
                contentDescription = null,
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.Fit
            )
        }
        Spacer(Modifier.height(12.dp))
        Text(
            text = title,
            style = MaterialTheme.typography.labelLarge,
            fontWeight = FontWeight.Bold,
            color = Color.White
        )
    }
}
