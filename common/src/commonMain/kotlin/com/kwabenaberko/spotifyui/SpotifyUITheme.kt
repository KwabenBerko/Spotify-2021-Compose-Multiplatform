package com.kwabenaberko.spotifyui

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Shapes
import androidx.compose.material3.Typography
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.Font
import spotifyui.common.generated.resources.Res
import spotifyui.common.generated.resources.avenir_next_bold
import spotifyui.common.generated.resources.avenir_next_demi
import spotifyui.common.generated.resources.avenir_next_medium
import spotifyui.common.generated.resources.avenir_next_regular

@OptIn(ExperimentalResourceApi::class)
@Composable
internal fun SpotifyUITheme(content: @Composable () -> Unit) {
    val avenir = FontFamily(
        Font(Res.font.avenir_next_bold, FontWeight.ExtraBold, FontStyle.Normal),
        Font(Res.font.avenir_next_demi, FontWeight.Bold, FontStyle.Normal),
        Font(Res.font.avenir_next_medium, FontWeight.Medium, FontStyle.Normal),
        Font(Res.font.avenir_next_regular, FontWeight.Normal, FontStyle.Normal)
    )
    val colorScheme = lightColorScheme(
        background = Color(0xFF121212),
        primary = Color(0xFF1ED760),
        tertiary = Color(0xFF777777)
    )
    val typography = Typography(
        headlineLarge = TextStyle(fontFamily = avenir, fontSize = 28.sp),
        headlineMedium = TextStyle(fontFamily = avenir, fontSize = 26.sp),
        headlineSmall = TextStyle(fontFamily = avenir, fontSize = 22.sp),
        titleLarge = TextStyle(fontFamily = avenir, fontSize = 20.sp),
        titleMedium = TextStyle(fontFamily = avenir, fontSize = 16.sp),
        titleSmall = TextStyle(fontFamily = avenir, fontSize = 14.sp),
        labelLarge = TextStyle(fontFamily = avenir, fontSize = 13.sp),
        labelMedium = TextStyle(fontFamily = avenir, fontSize = 12.sp),
        labelSmall = TextStyle(fontFamily = avenir, fontSize = 10.sp),
    )
    val shapes = Shapes()

    MaterialTheme(
        colorScheme = colorScheme,
        typography = typography,
        shapes = shapes,
        content = content
    )
}