package com.beardness.macosmsapp.ui.compose.component.textinput

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Close
import androidx.compose.material.icons.rounded.Keyboard
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import com.beardness.macosmsapp.ui.theme.additional.MacoDimens

@Composable
fun LoadingInputComponent(
    modifier: Modifier,
    loading: Boolean,
    input: String,
    onInputChanged: (new: String) -> Unit,
    clearInput: () -> Unit,
    placeholder: String,
    onClickIme: () -> Unit,
) {
    Box(
        modifier = modifier
            .height(intrinsicSize = IntrinsicSize.Min)
            .clip(shape = RoundedCornerShape(size = MacoDimens.dp16)),
    ) {
        TextField(
            modifier = Modifier
                .fillMaxWidth()
                .heightIn(min = MacoDimens.dp56, max = MacoDimens.dp64x2),
            value = input,
            onValueChange = onInputChanged,
            shape = RectangleShape,
            enabled = !loading,
            colors = TextFieldDefaults.colors(
                focusedTextColor = MaterialTheme.colorScheme.onBackground,
                unfocusedTextColor = MaterialTheme.colorScheme.onBackground,
                disabledTextColor = MaterialTheme.colorScheme.onBackground.copy(alpha = .5f),
                focusedContainerColor = MaterialTheme.colorScheme.onBackground.copy(alpha = .2f),
                unfocusedContainerColor = MaterialTheme.colorScheme.onBackground.copy(alpha = .1f),
                disabledContainerColor = MaterialTheme.colorScheme.onBackground.copy(alpha = .1f),
                cursorColor = MaterialTheme.colorScheme.onBackground,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
                disabledIndicatorColor = Color.Transparent,
            ),
            textStyle = MaterialTheme.typography.bodyMedium,
            trailingIcon = {
                Icon(
                    modifier = Modifier
                        .size(size = MacoDimens.dp24)
                        .clip(shape = MaterialTheme.shapes.extraSmall)
                        .clickable(onClick = clearInput),
                    imageVector = Icons.Rounded.Close,
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.onBackground,
                )
            },
            leadingIcon = {
                Icon(
                    modifier = Modifier
                        .size(size = MacoDimens.dp24),
                    imageVector = Icons.Rounded.Keyboard,
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.onBackground,
                )
            },
            placeholder = {
                Text(
                    text = placeholder,
                    style = MaterialTheme.typography.labelMedium,
                    color = MaterialTheme.colorScheme.onBackground.copy(alpha = .5f),
                )
            },
            keyboardOptions = KeyboardOptions(
                capitalization = KeyboardCapitalization.Sentences,
                imeAction = ImeAction.Send,
            ),
            keyboardActions = KeyboardActions(
                onSend = { onClickIme() }
            )
        )

        if (loading) {
            LinearProgressIndicator(
                modifier = Modifier
                    .fillMaxSize(),
                color = MaterialTheme.colorScheme.primary.copy(alpha = .3f),
                trackColor = MaterialTheme.colorScheme.onPrimary.copy(alpha = .3f)
            )
        }
    }
}