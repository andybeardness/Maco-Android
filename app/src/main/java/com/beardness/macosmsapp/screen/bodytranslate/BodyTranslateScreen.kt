package com.beardness.macosmsapp.screen.bodytranslate

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.beardness.macosmsapp.ui.component.sms.translate.TranslationComponent
import com.beardness.macosmsapp.ui.theme.dimen.Dimen
import com.beardness.macosmsapp.utils.SpecificChars

@Composable
fun BodyTranslateScreen(
    viewModel: BodyTranslateViewModelProtocol,
    initialInput: String = "",
) {
    val focusManager = LocalFocusManager.current

    var input by remember { mutableStateOf(initialInput) }

    val onInputChanged: (new: String) -> Unit = { new ->
        input = new
        viewModel.input(updated = new)
    }

    val translated by viewModel.translated.collectAsState(initial = null)

    val translatedAuto = translated?.translatedAuto ?: ""
    val translatedGe = translated?.translatedGe ?: ""

    val inProgress by viewModel.inProgress.collectAsState(initial = false)
    val internet by viewModel.internet.collectAsState(initial = false)

    val isTranslateButtonVisible = internet && input.isNotBlank()

    val onClickTranslate: (text: String) -> Unit = { text ->
        viewModel.translate(text = text)
        focusManager.clearFocus()
    }

    val onClickTranslatedText: (text: String) -> Unit = { text ->
        viewModel.copyToClipboard(text = text)
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {
        TextField(
            modifier = Modifier
                .fillMaxWidth()
                .weight(weight = 1f),
            value = input,
            onValueChange = onInputChanged,
            enabled = internet,
            placeholder = {
                Text(
                    text = "Input text here",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Light,
                    color = MaterialTheme.colorScheme.onBackground.copy(alpha = .3f),
                )
            },
            shape = RectangleShape,
            colors = TextFieldDefaults.colors(
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
                disabledIndicatorColor = Color.Transparent,
                errorIndicatorColor = Color.Transparent,
            )
        )

        AnimatedVisibility(visible = isTranslateButtonVisible) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                Box(
                    modifier = Modifier
                        .weight(weight = 1f)
                        .clickable { onClickTranslate(input) }
                        .background(color = MaterialTheme.colorScheme.onBackground.copy(alpha = .1f))
                        .padding(all = Dimen.dp16),
                    contentAlignment = Alignment.Center,
                ) {
                    Text(
                        text = "Translate ${SpecificChars.EMOJI_EARTH} ${SpecificChars.EMOJI_GEORGIA}",
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Light,
                        color = MaterialTheme.colorScheme.onBackground,
                    )
                }
            }
        }

        AnimatedVisibility(visible = inProgress) {
            LinearProgressIndicator(
                modifier = Modifier
                    .fillMaxWidth()
            )
        }

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .weight(weight = 1f)
                .scrollable(
                    state = rememberScrollState(),
                    orientation = Orientation.Vertical,
                )
                .padding(
                    horizontal = Dimen.dp16,
                    vertical = Dimen.dp8,
                )
        ) {
            TranslationComponent(
                visibility = translatedAuto.isNotEmpty(),
                title = "Translation ${SpecificChars.EMOJI_EARTH}",
                text = translatedAuto,
                onClick = { onClickTranslatedText(translatedAuto) },
            )

            Spacer(modifier = Modifier.height(height = Dimen.dp8))

            TranslationComponent(
                visibility = translatedGe.isNotEmpty(),
                title = "Georgia ${SpecificChars.EMOJI_GEORGIA}",
                text = translatedGe,
                onClick = { onClickTranslatedText(translatedGe) },
            )
        }
    }
}