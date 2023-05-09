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
import com.beardness.macosmsapp.extensions.AnimatedNullVisibility
import com.beardness.macosmsapp.screen.body.dto.TranslateViewState
import com.beardness.macosmsapp.ui.compose.component.sms.TranslateComponent
import com.beardness.macosmsapp.ui.compose.widget.gif.GifWidget
import com.beardness.macosmsapp.ui.theme.animation.MacoAnimations
import com.beardness.macosmsapp.ui.theme.dimen.Dimens

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun TranslateWidget(
    modifier: Modifier,
    state: TranslateViewState,
    onClickText: (text: String) -> Unit,
) {
    val translationAutoText = stringResource(id = R.string.translation_auto)
    val translationGeText = stringResource(id = R.string.translation_ge)

    val animationSpecFloat = MacoAnimations.faster<Float>()
    val animationSpecIntOffset = MacoAnimations.faster<IntOffset>()

    val animationSpecFloatDelayed = MacoAnimations.faster<Float>(delayMillis = MacoAnimations.DURATION.FASTER.millis)

    val translateVisibility = state is TranslateViewState.Translate

    val waitingResId =
        when (state) {
            TranslateViewState.Initial -> null
            TranslateViewState.NoTranslate -> R.drawable.waiting
            is TranslateViewState.Translate -> null
        }

    val translatedAuto =
        if (state is TranslateViewState.Translate) {
            state.translation.translatedAuto
        } else {
            ""
        }

    val translatedGe =
        if (state is TranslateViewState.Translate) {
            state.translation.translatedGe
        } else {
            ""
        }

    Box(
        modifier = modifier,
        contentAlignment = Alignment.Center
    ) {
        AnimatedVisibility(
            visible = translateVisibility,
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
                    onClickText = { onClickText(translatedAuto) },
                )

                Spacer(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = Dimens.dp12)
                        .height(height = Dimens.dp1)
                        .background(
                            color = MaterialTheme.colorScheme.onBackground.copy(alpha = .1f)
                        ),
                )

                TranslateComponent(
                    title = translationGeText,
                    text = translatedGe,
                    onClickText = { onClickText(translatedGe) },
                )
            }
        }

        AnimatedNullVisibility(
            nullable = waitingResId,
            enter = fadeIn(
                animationSpec = animationSpecFloatDelayed,
            ) + scaleIn(
                animationSpec = animationSpecFloatDelayed,
                initialScale = .2f
            ),
            exit = fadeOut(
                animationSpec = animationSpecFloat,
            ) + scaleOut(
                animationSpec = animationSpecFloat,
                targetScale = .2f
            ),
        ) { resId ->
            GifWidget(
                modifier = Modifier
                    .size(size = Dimens.dp64x3),
                gifResId = resId,
            )
        }
    }
}