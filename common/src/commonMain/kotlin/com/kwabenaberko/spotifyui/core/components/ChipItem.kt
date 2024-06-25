package com.kwabenaberko.spotifyui.core.components

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.kwabenaberko.spotifyui.fromHex

@Composable
fun ChipItem(
    title: String,
    onClicked: () -> Unit
) {
    Box(
        modifier = Modifier
            .border(
                width = 0.6.dp,
                color = Color.fromHex(hex = "7F7F7F"),
                shape = RoundedCornerShape(45.dp)
            )
            .padding(horizontal = 18.dp, vertical = 8.dp)
            .clickable(onClick = onClicked)
    ) {
        Text(
            text = title,
            style = MaterialTheme.typography.titleSmall,
            fontWeight = FontWeight.Medium,
            color = Color.White
        )
    }
}
