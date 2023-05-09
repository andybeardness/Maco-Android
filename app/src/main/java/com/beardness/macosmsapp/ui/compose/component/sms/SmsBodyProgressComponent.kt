package com.beardness.macosmsapp.ui.compose.component.sms

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color

@Composable
fun SmsBodyProgressComponent(
    body: String,
    backgroundColor: Color,
    contentColor: Color,
    processing: Boolean,
    onClickSmsBody: (() -> Unit)?,
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(intrinsicSize = IntrinsicSize.Min)
            .background(color = backgroundColor)
    ) {
        SmsBodyComponent(
            body = body,
            contentColor = contentColor,
            onClick = onClickSmsBody,
        )

        AnimatedVisibility(
            visible = processing
        ) {
            LinearProgressIndicator(
                modifier = Modifier
                    .fillMaxSize(),
                color = MaterialTheme.colorScheme.primary.copy(alpha = .1f),
                trackColor = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = .1f),
            )
        }
    }
}