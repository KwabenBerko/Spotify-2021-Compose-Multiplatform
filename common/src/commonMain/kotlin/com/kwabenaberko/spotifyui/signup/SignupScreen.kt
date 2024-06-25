package com.kwabenaberko.spotifyui.signup

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.core.tween
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.core.stack.mutableStateStackOf
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import com.kwabenaberko.spotifyui.SpotifyUIAppIcons
import com.kwabenaberko.spotifyui.core.IsUserLoggedIn
import com.kwabenaberko.spotifyui.core.model.Artist
import com.kwabenaberko.spotifyui.core.model.Podcast
import com.kwabenaberko.spotifyui.mockArtists
import com.kwabenaberko.spotifyui.mockPodcasts
import kotlinx.coroutines.launch
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.stringResource
import spotifyui.common.generated.resources.Res
import spotifyui.common.generated.resources.signup_choose_artists
import spotifyui.common.generated.resources.signup_create_account

private enum class SignupDestination {
    EMAIL,
    PASSWORD,
    GENDER,
    NAME,
    ARTISTS,
    PODCASTS
}

data class SignupState(
    val email: String = "",
    val password: String = "",
    val name: String = "",
    val sendNewsAndOffers: Boolean = false,
    val shareRegistrationData: Boolean = false,
    val artistSearchText: String = "",
    val artists: List<Artist> = emptyList(),
    val podcastSearchText: String = "",
    val podcasts: List<Podcast> = emptyList()
)

val mockSignupState = SignupState(
    email = "eobardthawne@gmail.com",
    password = "12345678",
    name = "Eobard Thawne",
    sendNewsAndOffers = false,
    shareRegistrationData = false,
    artistSearchText = "",
    artists = mockArtists,
    podcastSearchText = "",
    podcasts = mockPodcasts
)

internal class SignupScreen : Screen {
    @OptIn(ExperimentalResourceApi::class)
    @Composable
    override fun Content() {
        val navigator = LocalNavigator.currentOrThrow
        val isUserLoggedIn = IsUserLoggedIn.INSTANCE
        val scope = rememberCoroutineScope()
        val destinations = remember { mutableStateStackOf(SignupDestination.EMAIL) }
        val (state, setState) = remember { mutableStateOf(mockSignupState) }

        SignupScreenContent(
            destination = destinations.lastItemOrNull ?: SignupDestination.EMAIL,
            state = state,
            onEmailChanged = { email ->
                setState(state.copy(email = email))
            },
            onPasswordChanged = { password ->
                setState(state.copy(password = password))
            },
            onNameChanged = { name ->
                setState(state.copy(name = name))
            },
            onSendNewsAndOffersChanged = { sendNewsAndOffers ->
                setState(state.copy(sendNewsAndOffers = sendNewsAndOffers))
            },
            onShareRegistrationDataChanged = { shareRegistrationData ->
                setState(state.copy(shareRegistrationData = shareRegistrationData))
            },
            onArtistSearchTextChanged = { text ->
                setState(state.copy(artistSearchText = text))
            },
            onArtistSelected = { artist ->
                val artists = state.artists.toMutableList()
                val index = artists.indexOf(artist)
                val indexedArtist = artists[index]
                artists[index] = indexedArtist.copy(isSelected = !indexedArtist.isSelected)
                setState(state.copy(artists = artists.toList()))
            },
            onPodcastSearchTextChanged = { text ->
                setState(state.copy(podcastSearchText = text))
            },
            onPodcastSelected = { podcast ->
                val podcasts = state.podcasts.toMutableList()
                val index = podcasts.indexOf(podcast)
                val indexedPodcast = podcasts[index]
                podcasts[index] = indexedPodcast.copy(isSelected = !indexedPodcast.isSelected)
                setState(state.copy(podcasts = podcasts))
            },
            onBackClicked = {
                if (destinations.size == 1) {
                    navigator.pop()
                } else {
                    destinations.pop()
                }
            },
            onNextClicked = { destination ->
                destinations.push(destination)
            },
            onSignupCompletedClicked = {
                scope.launch {
                    isUserLoggedIn.flow.emit(true)
                }
            }
        )
    }
}

@Composable
private fun SignupScreenContent(
    destination: SignupDestination,
    state: SignupState,
    onEmailChanged: (String) -> Unit,
    onPasswordChanged: (String) -> Unit,
    onNameChanged: (String) -> Unit,
    onSendNewsAndOffersChanged: (Boolean) -> Unit,
    onShareRegistrationDataChanged: (Boolean) -> Unit,
    onArtistSearchTextChanged: (String) -> Unit,
    onPodcastSearchTextChanged: (String) -> Unit,
    onArtistSelected: (Artist) -> Unit,
    onPodcastSelected: (Podcast) -> Unit,
    onBackClicked: () -> Unit,
    onNextClicked: (SignupDestination) -> Unit,
    onSignupCompletedClicked: () -> Unit
) {
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = { AppBar(destination, onBackClicked) },
        containerColor = MaterialTheme.colorScheme.background,
    ) { paddingValues ->

        Box(Modifier.fillMaxSize().padding(paddingValues)) {
            AnimatedContent(
                targetState = destination,
                transitionSpec = {
                    if (targetState.ordinal > initialState.ordinal) {
                        slideInHorizontally(
                            animationSpec = tween(),
                            initialOffsetX = { fullWidth -> fullWidth }
                        ) togetherWith slideOutHorizontally(
                            animationSpec = tween(),
                            targetOffsetX = { fullWidth -> -fullWidth }
                        )
                    } else {
                        slideInHorizontally(
                            animationSpec = tween(),
                            initialOffsetX = { fullWidth -> -fullWidth }
                        ) togetherWith slideOutHorizontally(
                            animationSpec = tween(),
                            targetOffsetX = { fullWidth -> fullWidth }
                        )
                    }
                }
            ) { destination ->
                when (destination) {
                    SignupDestination.EMAIL -> EmailScreen(
                        email = state.email,
                        onEmailChanged = onEmailChanged,
                        onNextClicked = {
                            onNextClicked(SignupDestination.PASSWORD)
                        }
                    )

                    SignupDestination.PASSWORD -> PasswordScreen(
                        password = state.password,
                        onPasswordChanged = onPasswordChanged,
                        onNextClicked = {
                            onNextClicked(SignupDestination.GENDER)
                        }
                    )

                    SignupDestination.GENDER -> GenderScreen(
                        onNextClicked = {
                            onNextClicked(SignupDestination.NAME)
                        }
                    )

                    SignupDestination.NAME -> NameScreen(
                        name = state.name,
                        sendNewsAndOffers = state.sendNewsAndOffers,
                        shareRegistrationData = state.shareRegistrationData,
                        onNameChanged = onNameChanged,
                        onSendNewsAndOffersChanged = onSendNewsAndOffersChanged,
                        onShareRegistrationDataChanged = onShareRegistrationDataChanged,
                        onNextClicked = {
                            onNextClicked(SignupDestination.ARTISTS)
                        }
                    )

                    SignupDestination.ARTISTS -> ArtistsScreen(
                        searchText = state.artistSearchText,
                        artists = state.artists,
                        onSearchTextChanged = onArtistSearchTextChanged,
                        onArtistSelected = onArtistSelected,
                        onDoneClicked = {
                            onNextClicked(SignupDestination.PODCASTS)
                        }
                    )

                    SignupDestination.PODCASTS -> PodcastsScreen(
                        searchText = state.podcastSearchText,
                        podcasts = state.podcasts,
                        onSearchTextChanged = onPodcastSearchTextChanged,
                        onPodcastSelected = onPodcastSelected,
                        onDoneClicked = onSignupCompletedClicked
                    )
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun AppBar(
    destination: SignupDestination,
    onBackClicked: () -> Unit
) {
    CenterAlignedTopAppBar(
        title = {
            Text(
                text = destination.title(),
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.ExtraBold,
                maxLines = 2
            )
        },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = MaterialTheme.colorScheme.background,
            titleContentColor = Color.White
        ),
        navigationIcon = {
            IconButton(
                onClick = onBackClicked,
                modifier = Modifier
                    .padding(start = 10.dp)
                    .clip(CircleShape)
                    .background(Color.Black)
            ) {
                Icon(
                    imageVector = SpotifyUIAppIcons.Back,
                    contentDescription = null,
                    tint = Color.White
                )
            }
        }
    )
}

@OptIn(ExperimentalResourceApi::class)
@Composable
private fun SignupDestination.title(): String {
    return when (this) {
        SignupDestination.EMAIL,
        SignupDestination.PASSWORD,
        SignupDestination.GENDER,
        SignupDestination.NAME -> stringResource(Res.string.signup_create_account)

        SignupDestination.ARTISTS -> stringResource(Res.string.signup_choose_artists)
        SignupDestination.PODCASTS -> ""
    }
}
