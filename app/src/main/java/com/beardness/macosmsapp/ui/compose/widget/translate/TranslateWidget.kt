package com.beardness.macosmsapp.ui.compose.widget.translate

import androidx.compose.animation.*
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.IntOffset
import com.beardness.macosmsapp.R
import com.beardness.macosmsapp.screen.body.dto.BodyTranslatedViewDto
import com.beardness.macosmsapp.ui.compose.component.sms.TranslateComponent
import com.beardness.macosmsapp.ui.compose.widget.gif.GifWidget
import com.beardness.macosmsapp.ui.theme.animation.MacoAnimations
import com.beardness.macosmsapp.ui.theme.dimen.Dimens

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun TranslateWidget(
    modifier: Modifier,
    translated: BodyTranslatedViewDto?,
    onClickText: (text: String) -> Unit,
) {
    val translationAutoText = stringResource(id = R.string.translation_auto)
    val translationGeText = stringResource(id = R.string.translation_ge)

    val animationSpecFloat = MacoAnimations.faster<Float>()
    val animationSpecIntOffset = MacoAnimations.faster<IntOffset>()

    val visibility = translated != null

    val translatedAuto = translated?.translatedAuto ?: ""
    val translatedGe = translated?.translatedGe ?: ""

    Box(
        modifier = modifier,
        contentAlignment = Alignment.Center
    ) {
        AnimatedVisibility(
            visible = visibility,
            enter = fadeIn(
                animationSpec = animationSpecFloat,
            ) + slideInHorizontally(
                animationSpec = animationSpecIntOffset,
                initialOffsetX = { x -> - x / 2 }
            ),
            exit = fadeOut(
                animationSpec = animationSpecFloat,
            ) + slideOutHorizontally(
                animationSpec = animationSpecIntOffset,
                targetOffsetX = { x -> x / 2 }
            ),
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .verticalScroll(state = rememberScrollState())
            ) {
                TranslateComponent(
                    title = translationAutoText,
                    text = translatedAuto,
                    onClickText = { onClickText(translationAutoText) },
                )

                Spacer(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(height = Dimens.dp1)
                        .background(
                            color = MaterialTheme.colorScheme.onBackground.copy(alpha = .3f)
                        ),
                )

                TranslateComponent(
                    title = translationGeText,
                    text = translatedGe,
                    onClickText = { onClickText(translationAutoText) },
                )
            }
        }

        AnimatedVisibility(
            visible = !visibility,
            enter = fadeIn(
                animationSpec = animationSpecFloat
            ) + scaleIn(
                animationSpec = animationSpecFloat,
            ),
            exit = fadeOut(
                animationSpec = animationSpecFloat,
            ) + scaleOut(
                animationSpec = animationSpecFloat,
            ),
        ) {
            GifWidget(
                modifier = Modifier
                    .size(size = Dimens.dp64x3),
                gifResId = R.drawable.waiting,
            )
        }
    }
}