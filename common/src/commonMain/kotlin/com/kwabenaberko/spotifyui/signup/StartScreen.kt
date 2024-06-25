package com.kwabenaberko.spotifyui.signup

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import com.kwabenaberko.spotifyui.core.components.FlatButton
import com.kwabenaberko.spotifyui.core.components.PrimaryButton
import com.kwabenaberko.spotifyui.core.components.SocialButton
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource
import spotifyui.common.generated.resources.Res
import spotifyui.common.generated.resources.apple
import spotifyui.common.generated.resources.background
import spotifyui.common.generated.resources.facebook
import spotifyui.common.generated.resources.google
import spotifyui.common.generated.resources.login
import spotifyui.common.generated.resources.logo
import spotifyui.common.generated.resources.start_continue_with_apple
import spotifyui.common.generated.resources.start_continue_with_facebook
import spotifyui.common.generated.resources.start_continue_with_google
import spotifyui.common.generated.resources.start_sign_up_free
import spotifyui.common.generated.resources.start_spotify_title


class StartScreen : Screen {
    @Composable
    override fun Content() {
        val navigator = LocalNavigator.currentOrThrow

        StartScreenContent(
            onSignupFreeClicked = {
                navigator.push(SignupScreen())
            }
        )
    }
}

@Composable
private fun StartScreenContent(
    modifier: Modifier = Modifier,
    onSignupFreeClicked: () -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .then(modifier)
    ) {
        BackgroundImage()

        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.fillMaxSize()
                .padding(horizontal = 24.dp)
                .systemBarsPadding()

        ) {
            Spacer(modifier = Modifier.weight(0.75f))

            LogoAndText()

            Spacer(Modifier.height(30.dp))

            Buttons(onSignupFreeClicked)

            Spacer(Modifier.height(24.dp))
        }
    }
}

@OptIn(ExperimentalResourceApi::class)
@Composable
private fun BackgroundImage() {
    Image(
        painter = painterResource(Res.drawable.background),
        contentDescription = null,
        modifier = Modifier.fillMaxWidth(),
        contentScale = ContentScale.FillWidth
    )
}

@OptIn(ExperimentalResourceApi::class)
@Composable
private fun LogoAndText() {
    Image(
        painter = painterResource(Res.drawable.logo),
        contentDescription = null
    )
    Spacer(Modifier.height(10.dp))
    Text(
        text = stringResource(Res.string.start_spotify_title),
        color = Color.White,
        style = MaterialTheme.typography.headlineLarge.copy(lineHeight = 36.sp),
        fontWeight = FontWeight.ExtraBold,
        textAlign = TextAlign.Center,
    )
}

@OptIn(ExperimentalResourceApi::class)
@Composable
private fun Buttons(onSignupFreeClicked: () -> Unit) {
    Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
        PrimaryButton(
            text = stringResource(Res.string.start_sign_up_free),
            onClick = onSignupFreeClicked
        )
        SocialButton(
            image = painterResource(Res.drawable.google),
            text = stringResource(Res.string.start_continue_with_google),
            onClick = {}
        )
        SocialButton(
            image = painterResource(Res.drawable.facebook),
            text = stringResource(Res.string.start_continue_with_facebook),
            onClick = {}
        )
        SocialButton(
            image = painterResource(Res.drawable.apple),
            text = stringResource(Res.string.start_continue_with_apple),
            onClick = {}
        )
        FlatButton(
            text = stringResource(Res.string.login),
            onClick = {}
        )
    }
}
