package com.kwabenaberko.spotifyui

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.vectorResource
import spotifyui.common.generated.resources.Res
import spotifyui.common.generated.resources.ic_chevron_back_ios

@OptIn(ExperimentalResourceApi::class)
object SpotifyUIAppIcons {
    val Back: ImageVector
        @Composable
        get() {
            return when (platform) {
                Platform.Ios -> vectorResource(Res.drawable.ic_chevron_back_ios)
                else -> Icons.AutoMirrored.Default.ArrowBack
            }
        }
}
