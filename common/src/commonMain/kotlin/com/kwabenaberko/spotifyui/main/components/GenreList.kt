package com.kwabenaberko.spotifyui.main.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.kwabenaberko.spotifyui.core.components.VerticalGrid
import com.kwabenaberko.spotifyui.core.model.Genre

@Composable
fun GenreList(
    genres: List<Genre>,
    modifier: Modifier = Modifier,
    onGenreClicked: (Genre) -> Unit
) {
    VerticalGrid(
        items = genres,
        columns = 2,
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(8.dp),
        horizontalArrangement = Arrangement.spacedBy(10.dp)
    ) { genre ->
        GenreItem(
            genre = genre,
            onClicked = { onGenreClicked(genre) }
        )
    }
}

