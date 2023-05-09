package com.beardness.macosmsapp.ui.compose.component.sms

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import com.beardness.macosmsapp.screen.smsauthor.dto.SmsAuthorViewDto
import com.beardness.macosmsapp.screen.smsauthor.dto.exist
import com.beardness.macosmsapp.ui.theme.dimen.Dimens

@Composable
fun SmsCardComponent(
    sms: SmsAuthorViewDto,
    processing: Boolean,
    onClickBody: () -> Unit,
    onClickText: (text: String) -> Unit,
) {
    val isSmsTranslateExist = sms.translates.exist

    val onClickSmsBody =
        if (!isSmsTranslateExist) {
            onClickBody
        } else {
            null
        }

    val animatedBackgroundColor by animateColorAsState(
        targetValue = if (isSmsTranslateExist) {
            MaterialTheme.colorScheme.surfaceVariant
        } else {
            MaterialTheme.colorScheme.primaryContainer
        },
        animationSpec = tween(durationMillis = 350),
    )

    val animatedContentColor by animateColorAsState(
        targetValue = if (isSmsTranslateExist) {
            MaterialTheme.colorScheme.onSurfaceVariant
        } else {
            MaterialTheme.colorScheme.onPrimaryContainer
        },
        animationSpec = tween(durationMillis = 350),
    )
    
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = Dimens.dp16),
        shape = MaterialTheme.shapes.medium,
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface,
            contentColor = MaterialTheme.colorScheme.onSurface,
        ),
        border = BorderStroke(
            width = Dimens.dp1,
            color = MaterialTheme.colorScheme.onSurface.copy(alpha = .1f),
        ),
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            SmsBodyProgressComponent(
                body = sms.body,
                backgroundColor = animatedBackgroundColor,
                contentColor = animatedContentColor,
                processing = processing,
                onClickSmsBody = onClickSmsBody,
            )

            AnimatedVisibility(
                visible = isSmsTranslateExist,
            ) {
                SmsTranslateBlockComponent(
                    translatedAuto = sms.translates.auto,
                    translatedGe = sms.translates.georgian,
                    onClickTranslatedText = onClickText,
                )
            }
        }
    }
}