package com.kwabenaberko.spotifyui.controller

import androidx.compose.ui.window.ComposeUIViewController
import com.kwabenaberko.spotifyui.SpotifyUIApp
import platform.UIKit.UIImage
import platform.UIKit.UIViewController

class SpotifyUIViewControllerFactory {
    fun create(): UIViewController {
        return ComposeUIViewController {
            SpotifyUIApp()
        }
    }
}
