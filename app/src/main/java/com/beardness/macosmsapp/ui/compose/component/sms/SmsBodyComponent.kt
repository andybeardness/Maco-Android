package com.beardness.macosmsapp.ui.compose.component.sms

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Translate
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.beardness.macosmsapp.extensions.clickableIfNotNull
import com.beardness.macosmsapp.ui.theme.dimen.Dimens

@Composable
fun SmsBodyComponent(
    body: String,
    contentColor: Color,
    onClick: (() -> Unit)?,
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .clickableIfNotNull(onClick = onClick)
            .padding(all = Dimens.dp12),
        contentAlignment = Alignment.BottomEnd,
    ) {
        Text(
            modifier = Modifier
                .fillMaxWidth()
                .padding(end = Dimens.dp32),
            text = body,
            style = MaterialTheme.typography.bodyMedium,
            color = contentColor,
        )

        SmsIconComponent(
            modifier = Modifier
                .align(alignment = Alignment.BottomEnd),
            imageVector = Icons.Rounded.Translate,
        )
    }
}