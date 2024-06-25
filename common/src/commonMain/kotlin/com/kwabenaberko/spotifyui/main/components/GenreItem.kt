@file:OptIn(ExperimentalResourceApi::class)

package com.kwabenaberko.spotifyui.main.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.clipToBounds
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.kwabenaberko.spotifyui.core.model.Genre
import com.kwabenaberko.spotifyui.fromHex
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.painterResource

@OptIn(ExperimentalResourceApi::class)
@Composable
fun GenreItem(
    genre: Genre,
    modifier: Modifier = Modifier,
    onClicked: () -> Unit
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .height(109.dp)
            .background(Color.fromHex(hex = genre.hexColor), RoundedCornerShape(4.dp))
            .clickable(onClick = onClicked)
    ) {
        Title(
            text = genre.name,
            modifier = Modifier.weight(1f)
        )
        Artwork(
            image = genre.image,
            modifier = Modifier
        )
    }
}

@Composable
private fun Title(
    text: String,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .fillMaxSize()
            .padding(
                start = 16.dp,
                top = 16.dp,
                bottom = 16.dp,
                end = 4.dp
            )
    ) {
        Text(
            text = text,
            style = MaterialTheme.typography.titleMedium,
            fontWeight = FontWeight.ExtraBold,
            color = Color.White,
            textAlign = TextAlign.Left,
            lineHeight = 26.sp
        )
    }
}

@OptIn(ExperimentalResourceApi::class)
@Composable
private fun Artwork(
    image: DrawableResource?,
    modifier: Modifier = Modifier
) {
    val size = remember { DpSize(width = 66.dp, height = 66.dp) }
    Box(
        modifier = modifier.fillMaxHeight().clipToBounds(),
        contentAlignment = Alignment.BottomEnd
    ) {
        Box(
            modifier = Modifier
                .rotate(degrees = 19.86f)
                .offset(x = 16.dp, y = (-5).dp)
                .shadow(elevation = 5.dp, spotColor = Color.Red, ambientColor = Color.Green)
                .background(Color.fromHex(hex = "#1F1F21"))
                .size(size)
                .clip(RoundedCornerShape(2.dp)),
            contentAlignment = Alignment.Center
        ) {
            if (image == null) {
                Text(
                    text = "Album",
                    style = MaterialTheme.typography.labelMedium,
                    fontWeight = FontWeight.ExtraBold,
                    color = Color.White
                )
            } else {
                Image(
                    painter = painterResource(image),
                    contentDescription = null,
                    modifier = Modifier.width(size.width),
                    contentScale = ContentScale.Crop
                )
            }
        }
    }
}
