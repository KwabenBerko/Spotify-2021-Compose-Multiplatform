package com.kwabenaberko.spotifyui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.text.intl.Locale
import cafe.adriel.voyager.navigator.Navigator
import cafe.adriel.voyager.transitions.SlideTransition
import com.kwabenaberko.spotifyui.core.IsUserLoggedIn
import com.kwabenaberko.spotifyui.main.MainScreen
import com.kwabenaberko.spotifyui.signup.StartScreen

@Composable
internal fun AppScreen() {
    val useCase = remember { IsUserLoggedIn.INSTANCE }
    val isLoggedIn by useCase.execute().collectAsState(initial = false)

    SpotifyUITheme {
        if (isLoggedIn) {
            Navigator(MainScreen()) { navigator ->
                SlideTransition(navigator)
            }
        } else {
            Navigator(StartScreen()) { navigator ->
                SlideTransition(navigator)
            }
        }
    }
}
