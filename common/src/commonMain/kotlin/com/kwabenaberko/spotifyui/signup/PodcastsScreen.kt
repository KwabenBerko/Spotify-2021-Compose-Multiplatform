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
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.kwabenaberko.spotifyui.core.components.PodcastItem
import com.kwabenaberko.spotifyui.core.components.SearchTextField
import com.kwabenaberko.spotifyui.core.model.Podcast
import com.kwabenaberko.spotifyui.signup.components.SignupButton
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.stringResource
import spotifyui.common.generated.resources.Res
import spotifyui.common.generated.resources.done
import spotifyui.common.generated.resources.signup_choose_podcasts

@OptIn(ExperimentalResourceApi::class)
@Composable
fun PodcastsScreen(
    searchText: String,
    podcasts: List<Podcast>,
    modifier: Modifier = Modifier,
    onSearchTextChanged: (String) -> Unit,
    onPodcastSelected: (Podcast) -> Unit,
    onDoneClicked: () -> Unit
) {
    Box(modifier = modifier.fillMaxSize().padding(horizontal = 24.dp)) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.background)
        ) {

            Text(
                text = stringResource(Res.string.signup_choose_podcasts),
                modifier = Modifier.padding(end = 16.dp),
                style = MaterialTheme.typography.headlineMedium,
                fontWeight = FontWeight.ExtraBold,
                color = Color.White,
            )

            Spacer(Modifier.height(10.dp))

            SearchTextField(
                searchText = searchText,
                onTextChanged = onSearchTextChanged
            )

            Spacer(Modifier.height(24.dp))

            PodcastsGrid(
                podcasts = podcasts,
                onPodcastSelected = onPodcastSelected
            )
        }

        SignupButton(
            text = stringResource(Res.string.done),
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(bottom = 24.dp),
            onClick = onDoneClicked
        )
    }
}

@Composable
private fun PodcastsGrid(
    podcasts: List<Podcast>,
    modifier: Modifier = Modifier,
    onPodcastSelected: (Podcast) -> Unit
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
            items = podcasts,
            key = { podcast -> podcast.title }
        ) { podcast ->
            PodcastItem(podcast) {
                onPodcastSelected(podcast)
            }
        }
    }
}
