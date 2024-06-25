package com.kwabenaberko.spotifyui.signup

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.kwabenaberko.spotifyui.signup.components.SignupButton
import com.kwabenaberko.spotifyui.signup.components.SignupTextField
import com.kwabenaberko.spotifyui.signup.components.SignupTitleText
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.stringResource
import spotifyui.common.generated.resources.Res
import spotifyui.common.generated.resources.signup_gender_question
import spotifyui.common.generated.resources.signup_next

@OptIn(ExperimentalResourceApi::class)
@Composable
fun GenderScreen(onNextClicked: () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 24.dp)
            .padding(top = 28.dp)
    ) {

        SignupTitleText(text = stringResource(Res.string.signup_gender_question))

        SignupTextField(
            value = "",
            readOnly = true,
            onValueChanged = {}
        )

        Spacer(Modifier.height(40.dp))

        SignupButton(
            text = stringResource(Res.string.signup_next),
            onClick = onNextClicked
        )
    }
}