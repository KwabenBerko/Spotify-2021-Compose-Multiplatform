package com.kwabenaberko.spotifyui.main

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import com.kwabenaberko.spotifyui.core.components.SearchTextField
import com.kwabenaberko.spotifyui.core.model.Media
import com.kwabenaberko.spotifyui.fromHex
import com.kwabenaberko.spotifyui.main.components.MediaList
import com.kwabenaberko.spotifyui.mockRecentSearches
import kotlinx.coroutines.delay
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.stringResource
import spotifyui.common.generated.resources.Res
import spotifyui.common.generated.resources.cancel
import spotifyui.common.generated.resources.recent_searches
import spotifyui.common.generated.resources.search
import kotlin.time.Duration.Companion.seconds

data class RecentSearchesState(
    val searchText: String = "",
    var searches: List<Media> = emptyList()
)

val mockRecentSearchesState = RecentSearchesState(
    searchText = "",
    searches = mockRecentSearches
)

class RecentSearchesScreen : Screen {
    @Composable
    override fun Content() {
        val navigator = LocalNavigator.currentOrThrow
        val (state, setState) = remember { mutableStateOf(mockRecentSearchesState) }

        RecentSearchesScreenContent(
            state = state,
            onSearchTextChanged = { text ->
                setState(state.copy(searchText = text))
            },
            onBackClicked = navigator::pop
        )
    }
}

@OptIn(ExperimentalResourceApi::class)
@Composable
private fun RecentSearchesScreenContent(
    state: RecentSearchesState,
    onSearchTextChanged: (String) -> Unit,
    onBackClicked: () -> Unit
) {
    val focusRequester = remember { FocusRequester() }

    LaunchedEffect(Unit) {
        delay(0.5.seconds)
        focusRequester.requestFocus()
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .systemBarsPadding()
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 8.dp)
                .padding(horizontal = 16.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                SearchTextField(
                    searchText = state.searchText,
                    modifier = Modifier.weight(1f).focusRequester(focusRequester),
                    placeholderText = stringResource(Res.string.search),
                    onTextChanged = onSearchTextChanged,
                    textColor = Color.White,
                    backgroundColor = Color.fromHex(hex = "#282828"),
                    fontWeight = FontWeight.Medium,
                    cornerRadius = 10.dp,
                    contentPadding = PaddingValues(8.dp)
                )

                CancelButton(onClicked = onBackClicked)
            }

            Spacer(Modifier.height(36.dp))

            SearchList(
                searches = state.searches,
                onRecentSearchClicked = {}
            )
        }
    }
}

@OptIn(ExperimentalResourceApi::class)
@Composable
private fun SearchList(
    searches: List<Media>,
    modifier: Modifier = Modifier,
    onRecentSearchClicked: (Media) -> Unit
) {
    Column(modifier = modifier) {
        Text(
            text = stringResource(Res.string.recent_searches),
            style = MaterialTheme.typography.titleMedium,
            fontWeight = FontWeight.Bold,
            color = Color.White
        )

        Spacer(Modifier.height(16.dp))

        MediaList(
            mediaList = searches,
            onMediaClicked = onRecentSearchClicked
        )
    }
}

@OptIn(ExperimentalResourceApi::class)
@Composable
private fun CancelButton(modifier: Modifier = Modifier, onClicked: () -> Unit) {
    Text(
        text = stringResource(Res.string.cancel),
        modifier = modifier.clickable(onClick = onClicked),
        style = MaterialTheme.typography.titleMedium,
        color = Color.White,
        fontWeight = FontWeight.Medium
    )
}
