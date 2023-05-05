package com.beardness.macosmsapp.screen.bodytranslate

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.beardness.macosmsapp.ui.component.sms.translate.TranslationComponent
import com.beardness.macosmsapp.ui.theme.dimen.Dimen
import com.beardness.macosmsapp.utils.SpecificChars

@Composable
fun BodyTranslateScreen(
    viewModel: BodyTranslateViewModelProtocol,
) {
    val enteredState = viewModel.entered.collectAsState(initial = "")
    val entered = enteredState.value ?: ""

    val translated by viewModel.translated.collectAsState(initial = null)

    val translatedAuto = translated?.translatedAuto ?: ""
    val translatedGe = translated?.translatedGe ?: ""

    val inProgress by viewModel.inProgress.collectAsState(initial = false)

    val internet by viewModel.internet.collectAsState(initial = false)

    val isTranslateButtonVisible = internet && entered.isNotEmpty()

    val onEnteredChanged: (String) -> Unit = { new ->
        viewModel.updateEntered(text = new)
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {

        TextField(
            modifier = Modifier
                .fillMaxWidth()
                .weight(weight = 1f),
            value = entered,
            onValueChange = onEnteredChanged,
            enabled = internet,
            placeholder = {
                Text(
                    text = "Input text here",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Light,
                    color = MaterialTheme.colorScheme.onBackground.copy(alpha = .3f),
                )
            }
        )

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
                text = translatedAuto
            )

            TranslationComponent(
                visibility = translatedGe.isNotEmpty(),
                title = "Georgia ${SpecificChars.EMOJI_GEORGIA}",
                text = translatedGe
            )
        }

        AnimatedVisibility(visible = inProgress) {
            LinearProgressIndicator(
                modifier = Modifier
                    .fillMaxWidth()
            )
        }

        AnimatedVisibility(visible = isTranslateButtonVisible) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
            ) {

                Box(
                    modifier = Modifier
                        .weight(weight = 1f)
                        .clickable { viewModel.translate(text = entered) }
                        .padding(all = Dimen.dp16),
                    contentAlignment = Alignment.Center,
                ) {
                    Text(
                        text = "Translate ${SpecificChars.EMOJI_EARTH}",
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Light,
                        color = MaterialTheme.colorScheme.onBackground,
                    )
                }
            }
        }
    }
}