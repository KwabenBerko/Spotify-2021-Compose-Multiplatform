package com.kwabenaberko.spotifyui.main

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.kwabenaberko.spotifyui.core.components.ChipItem
import com.kwabenaberko.spotifyui.core.model.Media
import com.kwabenaberko.spotifyui.fromHex
import com.kwabenaberko.spotifyui.main.components.MediaList
import com.kwabenaberko.spotifyui.mockLibraryRecentlyPlayed
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource
import spotifyui.common.generated.resources.Res
import spotifyui.common.generated.resources.ic_add
import spotifyui.common.generated.resources.ic_grid
import spotifyui.common.generated.resources.ic_sort
import spotifyui.common.generated.resources.profile
import spotifyui.common.generated.resources.recently_played
import spotifyui.common.generated.resources.your_library

data class LibraryState(
    val filters: List<String> = emptyList(),
    val recentlyPlayed: List<Media> = emptyList()
)

val mockLibraryState = LibraryState(
    filters = listOf("Playlists", "Artists", "Albums", "Podcasts & shows"),
    recentlyPlayed = mockLibraryRecentlyPlayed
)

@Composable
fun LibraryScreen() {
    val state = remember { mockLibraryState }

    LibraryScreenContent(
        state = state,
        onFilterClicked = {},
        onRecentlyPlayedClicked = {}
    )
}

@Composable
fun LibraryScreenContent(
    state: LibraryState,
    onFilterClicked: (String) -> Unit,
    onRecentlyPlayedClicked: (Media) -> Unit
) {
    Box(modifier = Modifier.fillMaxSize().background(MaterialTheme.colorScheme.background)) {
        Column(modifier = Modifier.padding(top = 10.dp)) {
            Header(
                modifier = Modifier.padding(horizontal = 16.dp),
                onAddClicked = {}
            )
            Spacer(Modifier.height(28.dp))
            FiltersList(
                filters = state.filters,
                modifier = Modifier.padding(horizontal = 16.dp),
                onFilterClicked = onFilterClicked
            )
            Spacer(Modifier.height(20.dp))
            Column(modifier = Modifier.padding(16.dp)) {
                RecentlyPlayedHeader(
                    onSortClicked = {},
                    onGridClicked = {}
                )

                Spacer(Modifier.height(16.dp))

                MediaList(
                    mediaList = state.recentlyPlayed,
                    onMediaClicked = onRecentlyPlayedClicked
                )
            }
        }
    }
}

@OptIn(ExperimentalResourceApi::class)
@Composable
private fun Header(
    modifier: Modifier = Modifier,
    onAddClicked: () -> Unit
) {
    Row(
        modifier = Modifier.fillMaxWidth().then(modifier),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            ProfileImage()
            Spacer(modifier = Modifier.width(8.dp))
            Text(
                text = stringResource(Res.string.your_library),
                style = MaterialTheme.typography.headlineMedium,
                color = Color.White,
                fontWeight = FontWeight.Bold
            )
        }

        IconButton(onClick = onAddClicked) {
            Icon(
                painter = painterResource(Res.drawable.ic_add),
                contentDescription = null,
                modifier = Modifier.width(29.dp),
                tint = Color.fromHex(hex = "#A7A7A7")
            )
        }
    }
}

@OptIn(ExperimentalResourceApi::class)
@Composable
private fun ProfileImage() {
    Box(
        modifier = Modifier
            .size(35.dp)
            .clip(CircleShape)
            .background(Color.fromHex(hex = "#3EC7EC"))
    ) {
        Image(
            painter = painterResource(Res.drawable.profile),
            contentDescription = null,
            modifier = Modifier.matchParentSize(),
            contentScale = ContentScale.Crop
        )
    }
}

@Composable
private fun FiltersList(
    filters: List<String>,
    modifier: Modifier = Modifier,
    onFilterClicked: (String) -> Unit
) {
    val scrollState = rememberScrollState()

    Row(
        modifier = Modifier.fillMaxWidth().horizontalScroll(scrollState).then(modifier),
        horizontalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        filters.forEach { filter ->
            ChipItem(
                title = filter,
                onClicked = { onFilterClicked(filter) }
            )
        }
    }
}

@OptIn(ExperimentalResourceApi::class)
@Composable
private fun RecentlyPlayedHeader(
    modifier: Modifier = Modifier,
    onSortClicked: () -> Unit,
    onGridClicked: () -> Unit
) {
    Row(
        modifier = Modifier.fillMaxWidth().then(modifier),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Row(
            modifier = Modifier.clickable(onClick = onSortClicked),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                painter = painterResource(Res.drawable.ic_sort),
                contentDescription = null,
                tint = Color.White
            )
            Spacer(modifier = Modifier.width(4.dp))
            Text(
                text = stringResource(Res.string.recently_played),
                style = MaterialTheme.typography.labelMedium,
                fontWeight = FontWeight.Bold,
                color = Color.White
            )
        }

        IconButton(onClick = onGridClicked) {
            Icon(
                painter = painterResource(Res.drawable.ic_grid),
                contentDescription = null,
                tint = Color.White
            )
        }
    }
}