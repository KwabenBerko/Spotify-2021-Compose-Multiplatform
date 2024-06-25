package com.kwabenaberko.spotifyui.core.components

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource
import spotifyui.common.generated.resources.Res
import spotifyui.common.generated.resources.ic_search
import spotifyui.common.generated.resources.search

@OptIn(ExperimentalResourceApi::class)
@Composable
fun SearchTextField(
    searchText: String,
    onTextChanged: (String) -> Unit,
    modifier: Modifier = Modifier,
    placeholderText: String = stringResource(Res.string.search),
    textColor: Color = Color.Black,
    backgroundColor: Color = Color.White,
    fontWeight: FontWeight = FontWeight.Medium,
    iconSize: DpSize = DpSize(width = 15.dp, height = 15.dp),
    cornerRadius: Dp = 5.dp,
    contentPadding: PaddingValues = PaddingValues(14.dp)
) {
    val textStyle = MaterialTheme.typography.titleMedium.copy(fontWeight = fontWeight)

    TextField(
        value = searchText,
        modifier = modifier,
        onValueChanged = onTextChanged,
        textColor = textColor,
        backgroundColor = backgroundColor,
        textStyle = textStyle,
        contentPadding = contentPadding,
        shape = RoundedCornerShape(cornerRadius),
        placeholder = {
            Text(
                text = placeholderText,
                style = textStyle,
                color = textColor
            )
        },
        leadingIcon = {
            Icon(
                painter = painterResource(Res.drawable.ic_search),
                contentDescription = null,
                modifier = Modifier.size(iconSize),
                tint = textColor
            )
        }
    )
}
