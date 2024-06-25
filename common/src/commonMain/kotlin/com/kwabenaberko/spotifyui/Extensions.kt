package com.kwabenaberko.spotifyui

import androidx.compose.ui.graphics.Color

fun Color.Companion.fromHex(hex: String): Color {
    val cleanedHex = if (hex.startsWith("#")) hex.substring(1) else hex

    val red = cleanedHex.substring(0, 2).toInt(16) / 255f
    val green = cleanedHex.substring(2, 4).toInt(16) / 255f
    val blue = cleanedHex.substring(4, 6).toInt(16) / 255f

    return Color(red, green, blue)
}