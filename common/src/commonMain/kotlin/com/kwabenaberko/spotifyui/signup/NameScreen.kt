package com.kwabenaberko.spotifyui.signup

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.CheckCircle
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconToggleButton
import androidx.compose.material3.LocalMinimumInteractiveComponentEnforcement
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.kwabenaberko.spotifyui.signup.components.SignupButton
import com.kwabenaberko.spotifyui.signup.components.SignupTextField
import com.kwabenaberko.spotifyui.signup.components.SignupTitleText
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.stringResource
import spotifyui.common.generated.resources.Res
import spotifyui.common.generated.resources.signup_create_an_account
import spotifyui.common.generated.resources.signup_name_profile_heads_up
import spotifyui.common.generated.resources.signup_name_question
import spotifyui.common.generated.resources.signup_privacy_policy
import spotifyui.common.generated.resources.signup_privacy_policy_info
import spotifyui.common.generated.resources.signup_send_news_and_offers
import spotifyui.common.generated.resources.signup_share_registration_data
import spotifyui.common.generated.resources.signup_terms_of_use
import spotifyui.common.generated.resources.signup_terms_of_use_info

@OptIn(ExperimentalMaterial3Api::class, ExperimentalResourceApi::class)
@Composable
fun NameScreen(
    name: String,
    sendNewsAndOffers: Boolean,
    shareRegistrationData: Boolean,
    onNameChanged: (String) -> Unit,
    onSendNewsAndOffersChanged: (Boolean) -> Unit,
    onShareRegistrationDataChanged: (Boolean) -> Unit,
    onNextClicked: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 24.dp)
            .padding(top = 28.dp)
    ) {

        SignupTitleText(text = stringResource(Res.string.signup_name_question))

        SignupTextField(
            value = name,
            footnote = stringResource(Res.string.signup_name_profile_heads_up),
            onValueChanged = onNameChanged
        )

        Spacer(Modifier.height(26.dp))

        Column(
            modifier = Modifier.weight(1f),
            verticalArrangement = Arrangement.spacedBy(24.dp)
        ) {
            HorizontalDivider(thickness = 1.dp, color = MaterialTheme.colorScheme.tertiary)

            Text(
                text = stringResource(Res.string.signup_terms_of_use_info),
                style = MaterialTheme.typography.labelMedium,
                fontWeight = FontWeight.Bold,
                color = Color.White,
            )

            Text(
                text = stringResource(Res.string.signup_terms_of_use),
                style = MaterialTheme.typography.labelMedium,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.primary
            )

            Text(
                text = stringResource(Res.string.signup_privacy_policy_info),
                style = MaterialTheme.typography.labelMedium,
                fontWeight = FontWeight.Bold,
                color = Color.White
            )

            Text(
                text = stringResource(Res.string.signup_privacy_policy),
                style = MaterialTheme.typography.labelMedium,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.primary
            )

            CheckBox(
                text = stringResource(Res.string.signup_send_news_and_offers),
                isChecked = sendNewsAndOffers,
                onCheckedChanged = onSendNewsAndOffersChanged
            )

            CheckBox(
                text = stringResource(Res.string.signup_share_registration_data),
                isChecked = shareRegistrationData,
                onCheckedChanged = onShareRegistrationDataChanged
            )
        }

        SignupButton(
            text = stringResource(Res.string.signup_create_an_account),
            onClick = onNextClicked
        )

        Spacer(Modifier.height(56.dp))
    }
}

@ExperimentalMaterial3Api
@Composable
private fun CheckBox(
    text: String,
    isChecked: Boolean,
    modifier: Modifier = Modifier,
    onCheckedChanged: (Boolean) -> Unit
) {
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier.fillMaxWidth()
    ) {
        Text(
            text = text,
            modifier = Modifier.weight(1f),
            style = MaterialTheme.typography.labelMedium,
            fontWeight = FontWeight.Bold,
            color = Color.White
        )

        CompositionLocalProvider(LocalMinimumInteractiveComponentEnforcement provides false) {
            IconToggleButton(
                checked = isChecked,
                onCheckedChange = onCheckedChanged,
                modifier = Modifier.size(24.dp)
            ) {
                Icon(
                    imageVector = Icons.Rounded.CheckCircle,
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.primary
                )
            }
        }
    }
}