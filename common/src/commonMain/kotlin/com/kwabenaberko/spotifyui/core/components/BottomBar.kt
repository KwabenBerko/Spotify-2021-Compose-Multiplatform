package com.kwabenaberko.spotifyui.core.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.StringResource
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource

@OptIn(ExperimentalResourceApi::class)
data class BottomBarTab(
    val icon: DrawableResource,
    val title: StringResource
)

@OptIn(ExperimentalResourceApi::class)
@Composable
fun BottomBarTabItem(
    tab: BottomBarTab,
    isSelected: Boolean,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = Modifier.fillMaxWidth().then(modifier),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Icon(
            painter = painterResource(tab.icon),
            contentDescription = null,
            tint = if (isSelected) {
                Color.White
            } else {
                MaterialTheme.colorScheme.tertiary
            }
        )
        Text(
            text = stringResource(tab.title),
            style = MaterialTheme.typography.labelLarge,
            color = if (isSelected) {
                Color.White
            } else {
                MaterialTheme.colorScheme.tertiary
            }
        )
    }
}

@Composable
fun BottomBar(
    tabs: List<BottomBarTab>,
    activeTab: BottomBarTab,
    modifier: Modifier = Modifier,
    onTabClicked: (BottomBarTab) -> Unit
) {
    Row(modifier = Modifier.wrapContentSize().then(modifier)) {
        tabs.forEach { tab ->
            BottomBarTabItem(
                tab = tab,
                isSelected = tab == activeTab,
                modifier = Modifier.weight(1f)
                    .clickable {
                        onTabClicked(tab)
                    },
            )
        }
    }
}
