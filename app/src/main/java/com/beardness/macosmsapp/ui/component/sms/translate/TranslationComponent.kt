package com.beardness.macosmsapp.ui.component.sms.translate

import androidx.compose.animation.AnimatedVisibility
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
) {
    AnimatedVisibility(visible = visibility) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = Dimen.dp8)
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