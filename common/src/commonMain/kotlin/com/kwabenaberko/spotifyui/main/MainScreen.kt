package com.kwabenaberko.spotifyui.main

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalViewConfiguration
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import com.kwabenaberko.spotifyui.core.components.BottomBar
import com.kwabenaberko.spotifyui.core.components.BottomBarTab
import com.kwabenaberko.spotifyui.core.components.TopBar
import com.kwabenaberko.spotifyui.core.components.TopBarAction
import com.kwabenaberko.spotifyui.fromHex
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource
import spotifyui.common.generated.resources.Res
import spotifyui.common.generated.resources.home
import spotifyui.common.generated.resources.ic_bluetooth
import spotifyui.common.generated.resources.ic_home
import spotifyui.common.generated.resources.ic_library
import spotifyui.common.generated.resources.ic_notification
import spotifyui.common.generated.resources.ic_pause
import spotifyui.common.generated.resources.ic_recents
import spotifyui.common.generated.resources.ic_search
import spotifyui.common.generated.resources.ic_settings
import spotifyui.common.generated.resources.one_remastered
import spotifyui.common.generated.resources.recently_played
import spotifyui.common.generated.resources.search
import spotifyui.common.generated.resources.your_library

class MainScreen : Screen {
    @Composable
    override fun Content() {
        val navigator = LocalNavigator.currentOrThrow

        MainScreenContent(
            onRecentSearchesClicked = {
                navigator.push(RecentSearchesScreen())
            }
        )
    }
}

@OptIn(ExperimentalResourceApi::class, ExperimentalMaterial3Api::class)
@Composable
private fun MainScreenContent(
    modifier: Modifier = Modifier,
    onRecentSearchesClicked: () -> Unit
) {
    val home = remember {
        BottomBarTab(icon = Res.drawable.ic_home, title = Res.string.home)
    }
    val search = remember {
        BottomBarTab(icon = Res.drawable.ic_search, title = Res.string.search)
    }
    val library = remember {
        BottomBarTab(icon = Res.drawable.ic_library, title = Res.string.your_library)
    }
    val tabs = remember { listOf(home, search, library) }
    val (activeTab, setActiveTab) = remember { mutableStateOf(home) }

    Scaffold(
        modifier = Modifier.fillMaxSize().then(modifier),
        topBar = {
            if (activeTab == home) {
                TopBar(
                    title = stringResource(Res.string.recently_played),
                    actions = {
                        TopBarAction(Res.drawable.ic_notification, onClick = {})
                        TopBarAction(Res.drawable.ic_recents, onClick = onRecentSearchesClicked)
                        TopBarAction(Res.drawable.ic_settings, onClick = {})
                    }
                )
            }
        },
        content = { paddingValues ->
            Box(modifier = Modifier.padding(paddingValues)) {
                when (activeTab) {
                    home -> HomeScreen()
                    search -> SearchScreen()
                    library -> LibraryScreen()
                }
            }
        },
        bottomBar = {
            Column {
                NowPlaying(modifier = Modifier.padding(horizontal = 6.dp))
                BottomBar(
                    tabs = tabs,
                    activeTab = activeTab,
                    modifier = Modifier
                        .padding(horizontal = 10.dp, vertical = 6.dp)
                        .navigationBarsPadding(),
                    onTabClicked = setActiveTab
                )
            }
        },
        containerColor = MaterialTheme.colorScheme.background
    )
}

@OptIn(ExperimentalResourceApi::class)
@Composable
private fun NowPlaying(modifier: Modifier = Modifier) {
    Column(modifier = modifier) {
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = modifier
                .fillMaxWidth()
                .height(60.dp)
                .background(Color.fromHex(hex = "#370D17"), RoundedCornerShape(12.dp))
                .padding(horizontal = 10.dp, vertical = 12.dp)
        ) {
            Row(modifier = Modifier.weight(1f)) {
                Artwork()
                Spacer(modifier = Modifier.width(10.dp))
                Column {
                    Track()
                    BluetoothDevice()
                }
            }
            Spacer(modifier = Modifier.weight(0.05f))
            Row(
                horizontalArrangement = Arrangement.End,
                verticalAlignment = Alignment.CenterVertically,
            ) {
                BluetoothIcon(modifier = Modifier.width(14.dp))
                Spacer(modifier = Modifier.width(18.dp))
                PauseIcon(onClick = {})
            }
        }
        TrackProgress()
    }
}

@OptIn(ExperimentalResourceApi::class)
@Composable
private fun Artwork(modifier: Modifier = Modifier) {
    Box(
        modifier = modifier
            .clip(RoundedCornerShape(6.dp))
            .background(Color.Black)
            .size(36.dp)
    ) {
        Image(
            painter = painterResource(Res.drawable.one_remastered),
            contentDescription = null,
            contentScale = ContentScale.Crop
        )
    }
}

@Composable
private fun Track(modifier: Modifier = Modifier) {
    Text(
        text = "From Me to You - Mono/Remastered • The Beetles",
        modifier = modifier,
        style = MaterialTheme.typography.labelLarge,
        fontWeight = FontWeight.Bold,
        color = Color.White,
        maxLines = 1,
        overflow = TextOverflow.Ellipsis
    )
}

@Composable
private fun TrackProgress(modifier: Modifier = Modifier) {
    LinearProgressIndicator(
        progress = { 0.53f },
        modifier = modifier
            .fillMaxWidth()
            .offset(y = (-4).dp)
            .padding(horizontal = 8.dp)
            .clip(RoundedCornerShape(8.dp)),
        color = Color.fromHex(hex = "#B2B2B2"),
        trackColor = Color.fromHex(hex = "#5E2432"),
        strokeCap = StrokeCap.Round
    )
}

@ExperimentalResourceApi
@Composable
private fun BluetoothIcon(modifier: Modifier = Modifier) {
    Icon(
        painter = painterResource(Res.drawable.ic_bluetooth),
        contentDescription = null,
        modifier = modifier.fillMaxHeight(),
        tint = MaterialTheme.colorScheme.primary
    )
}

@ExperimentalResourceApi
@Composable
private fun PauseIcon(
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {
    Icon(
        painter = painterResource(Res.drawable.ic_pause),
        contentDescription = null,
        modifier = Modifier.clickable(onClick = onClick).then(modifier),
        tint = Color.White
    )
}

@OptIn(ExperimentalResourceApi::class)
@Composable
private fun BluetoothDevice(modifier: Modifier = Modifier) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        BluetoothIcon(modifier = Modifier.width(7.dp))
        Spacer(modifier = Modifier.width(6.dp))
        Text(
            text = "BEATSPILL+",
            modifier = Modifier.wrapContentHeight(),
            style = MaterialTheme.typography.labelSmall,
            color = MaterialTheme.colorScheme.primary
        )
    }
}
