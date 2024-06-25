@file:OptIn(ExperimentalResourceApi::class)

package com.kwabenaberko.spotifyui.main

import androidx.compose.foundation.background
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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import com.kwabenaberko.spotifyui.core.components.SearchTextField
import com.kwabenaberko.spotifyui.core.model.Genre
import com.kwabenaberko.spotifyui.fromHex
import com.kwabenaberko.spotifyui.main.components.GenreList
import com.kwabenaberko.spotifyui.mockGenres
import com.kwabenaberko.spotifyui.mockPopularGenres
import com.kwabenaberko.spotifyui.mockTopGenres
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource
import spotifyui.common.generated.resources.Res
import spotifyui.common.generated.resources.browse_all
import spotifyui.common.generated.resources.ic_camera
import spotifyui.common.generated.resources.search
import spotifyui.common.generated.resources.search_artists_songs_podcasts
import spotifyui.common.generated.resources.search_popular_podcast_categories
import spotifyui.common.generated.resources.search_your_top_genres

data class SearchState(
    val searchText: String = "",
    val topGenres: List<Genre> = emptyList(),
    val popularGenres: List<Genre> = emptyList(),
    val genres: List<Genre> = emptyList()
)

val mockSearchState = SearchState(
    searchText = "",
    topGenres = mockTopGenres,
    popularGenres = mockPopularGenres,
    genres = mockGenres
)

@Composable
fun SearchScreen() {
    val (state, setState) = remember { mutableStateOf(mockSearchState) }
    SearchScreenContent(
        state = state,
        onSearchTextChanged = {
            setState(state.copy(searchText = it))
        },
        onCameraClicked = {}
    )
}

@OptIn(ExperimentalResourceApi::class)
@Composable
private fun SearchScreenContent(
    state: SearchState,
    onSearchTextChanged: (String) -> Unit,
    onCameraClicked: () -> Unit
) {

    Box(modifier = Modifier.fillMaxSize().background(MaterialTheme.colorScheme.background)) {
        Column(modifier = Modifier.fillMaxSize().padding(top = 10.dp)) {
            Column(modifier = Modifier.padding(horizontal = 16.dp)) {
                Header(onCameraClicked = onCameraClicked)
                Spacer(modifier = Modifier.height(20.dp))
                SearchBar(
                    searchText = state.searchText,
                    onSearchTextChanged = onSearchTextChanged
                )
            }

            Spacer(modifier = Modifier.height(22.dp))

            Column(modifier = Modifier.verticalScroll(rememberScrollState())) {
                GenreSection(
                    title = stringResource(Res.string.search_your_top_genres),
                    genres = state.topGenres,
                    onGenreClicked = {}
                )

                Spacer(modifier = Modifier.height(26.dp))

                GenreSection(
                    title = stringResource(Res.string.search_popular_podcast_categories),
                    genres = state.popularGenres,
                    onGenreClicked = {}
                )

                Spacer(modifier = Modifier.height(26.dp))

                GenreSection(
                    title = stringResource(Res.string.browse_all),
                    genres = state.genres,
                    onGenreClicked = {}
                )
            }
        }
    }
}

@OptIn(ExperimentalResourceApi::class)
@Composable
private fun Header(onCameraClicked: () -> Unit) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = stringResource(Res.string.search),
            style = MaterialTheme.typography.headlineMedium,
            color = Color.White,
            fontWeight = FontWeight.ExtraBold
        )

        IconButton(onClick = onCameraClicked) {
            Icon(
                painter = painterResource(Res.drawable.ic_camera),
                contentDescription = null,
                modifier = Modifier.width(29.dp),
                tint = Color.White
            )
        }
    }
}

@OptIn(ExperimentalResourceApi::class)
@Composable
private fun SearchBar(
    searchText: String,
    modifier: Modifier = Modifier,
    onSearchTextChanged: (String) -> Unit
) {
    SearchTextField(
        searchText = searchText,
        placeholderText = stringResource(Res.string.search_artists_songs_podcasts),
        modifier = modifier,
        onTextChanged = onSearchTextChanged,
        textColor = Color.fromHex(hex = "#131313"),
        iconSize = DpSize(width = 20.dp, height = 20.dp),
        contentPadding = PaddingValues(10.dp)
    )
}

@Composable
private fun GenreSection(
    title: String,
    genres: List<Genre>,
    modifier: Modifier = Modifier,
    onGenreClicked: (Genre) -> Unit
) {
    Column(modifier = modifier.fillMaxWidth().padding(horizontal = 16.dp)) {
        Text(
            text = title,
            style = MaterialTheme.typography.titleMedium,
            fontWeight = FontWeight.Bold,
            color = Color.White
        )

        Spacer(modifier = Modifier.height(16.dp))

        GenreList(
            genres = genres,
            onGenreClicked = onGenreClicked
        )
    }
}
