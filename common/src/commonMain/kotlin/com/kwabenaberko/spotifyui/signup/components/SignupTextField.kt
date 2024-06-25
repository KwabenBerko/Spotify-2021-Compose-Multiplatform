package com.kwabenaberko.spotifyui.signup.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import com.kwabenaberko.spotifyui.core.components.TextField

@Composable
fun SignupTextField(
    value: String,
    modifier: Modifier = Modifier,
    readOnly: Boolean = false,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    footnote: String? = null,
    onValueChanged: (String) -> Unit
) {
    Column(modifier = modifier.fillMaxWidth()) {
        TextField(
            value = value,
            modifier = Modifier.fillMaxWidth(),
            onValueChanged = onValueChanged,
            readOnly = readOnly,
            textColor = Color.White,
            backgroundColor = MaterialTheme.colorScheme.tertiary,
            singleLine = true,
            textStyle = MaterialTheme.typography.titleSmall.copy(fontWeight = FontWeight.Bold),
            shape = RoundedCornerShape(5.dp),
            keyboardOptions = keyboardOptions,
            visualTransformation = if (keyboardOptions.keyboardType == KeyboardType.Password) {
                PasswordVisualTransformation()
            } else {
                VisualTransformation.None
            }
        )

        Spacer(Modifier.height(10.dp))

        Row(modifier = Modifier.alpha(if (footnote == null) 0.0f else 1.0f)) {
            footnote?.let { footnote ->
                Text(
                    text = footnote,
                    style = MaterialTheme.typography.labelSmall,
                    fontWeight = FontWeight.Bold,
                    color = Color.White
                )
            }
        }
    }
}
