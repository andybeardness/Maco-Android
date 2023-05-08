package com.beardness.macosmsapp.ui.compose.sms

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.CopyAll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.beardness.macosmsapp.ui.compose.spacer.SpacerColumn
import com.beardness.macosmsapp.ui.theme.dimen.Dimens

@Composable
fun SmsTranslateComponent(
    title: String,
    text: String,
    onClickText: () -> Unit,
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClickText() }
            .padding(horizontal = Dimens.dp12)
            .padding(bottom = Dimens.dp8),
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth(),
        ) {
            SpacerColumn(dp = Dimens.dp8)

            Text(
                modifier = Modifier
                    .fillMaxWidth(),
                text = title,
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurface.copy(alpha = .5f),
            )

            SpacerColumn(dp = Dimens.dp8)

            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(end = Dimens.dp32),
                text = text,
                style = MaterialTheme.typography.bodyMedium,
            )

            SpacerColumn(dp = Dimens.dp8)
        }

        SmsIconComponent(
            modifier = Modifier
                .align(alignment = Alignment.BottomEnd)
                .padding(vertical = Dimens.dp8),
            imageVector = Icons.Rounded.CopyAll,
        )
    }
}