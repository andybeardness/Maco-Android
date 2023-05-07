package com.beardness.macosmsapp.ui.component.sms.translate

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.EnterTransition
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.slideInVertically
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.beardness.macosmsapp.ui.theme.dimen.Dimen

@Composable
fun TranslationComponent(
    visibility: Boolean,
    title: String,
    text: String,
    onClick: () -> Unit,
) {
    AnimatedVisibility(
        visible = visibility,
        enter = slideInVertically(
            animationSpec = tween(
                durationMillis = 350,
                delayMillis = 350,
            ),
            initialOffsetY = { size -> - size / 10 },
        ) + fadeIn(
            animationSpec = tween(
                durationMillis = 350,
                delayMillis = 350),
        ),
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .clickable { onClick() }
        ) {
            Text(
                text = title,
                fontSize = 12.sp,
                fontWeight = FontWeight.Light,
                color = MaterialTheme.colorScheme.onBackground,
            )

            Text(
                text = text,
                fontSize = 16.sp,
                fontWeight = FontWeight.Light,
                color = MaterialTheme.colorScheme.primary,
            )
        }
    }
}