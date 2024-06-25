package com.kwabenaberko.spotifyui.signup

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.kwabenaberko.spotifyui.core.components.ArtistItem
import com.kwabenaberko.spotifyui.core.components.SearchTextField
import com.kwabenaberko.spotifyui.core.model.Artist
import com.kwabenaberko.spotifyui.signup.components.SignupButton
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.stringResource
import spotifyui.common.generated.resources.Res
import spotifyui.common.generated.resources.done

@OptIn(ExperimentalResourceApi::class)
@Composable
fun ArtistsScreen(
    searchText: String,
    artists: List<Artist>,
    modifier: Modifier = Modifier,
    onSearchTextChanged: (String) -> Unit,
    onArtistSelected: (Artist) -> Unit,
    onDoneClicked: () -> Unit
) {
    Box(modifier = modifier.fillMaxSize().padding(horizontal = 24.dp)) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.background)
        ) {

            SearchTextField(
                searchText = searchText,
                onTextChanged = onSearchTextChanged
            )

            Spacer(Modifier.height(20.dp))

            ArtistsGrid(
                artists = artists,
                onArtistSelected = onArtistSelected
            )
        }

        if (showDoneButton(artists)) {
            SignupButton(
                text = stringResource(Res.string.done),
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .padding(bottom = 24.dp),
                onClick = onDoneClicked
            )
        }
    }
}

@Composable
private fun ArtistsGrid(
    artists: List<Artist>,
    modifier: Modifier = Modifier,
    onArtistSelected: (Artist) -> Unit
) {
    val columns = GridCells.Adaptive(minSize = 90.dp)

    LazyVerticalGrid(
        columns = columns,
        modifier = modifier,
        contentPadding = PaddingValues(vertical = 12.dp),
        verticalArrangement = Arrangement.spacedBy(24.dp),
        horizontalArrangement = Arrangement.spacedBy(12.dp),
    ) {
        items(
            items = artists,
            key = { artist -> artist.name }
        ) { artist ->
            ArtistItem(artist) {
                onArtistSelected(artist)
            }
        }
    }
}

private fun showDoneButton(artists: List<Artist>): Boolean {
    val size = artists
        .filter { it.isSelected }
        .size

    return size >= 3
}
