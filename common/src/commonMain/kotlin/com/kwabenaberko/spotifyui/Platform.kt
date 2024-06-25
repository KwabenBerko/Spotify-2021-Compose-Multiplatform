package com.kwabenaberko.spotifyui

sealed class Platform {
    data object Ios : Platform()
    data object Android : Platform()
}

expect val platform: Platform