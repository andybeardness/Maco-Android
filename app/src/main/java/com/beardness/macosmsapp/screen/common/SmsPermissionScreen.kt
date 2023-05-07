package com.beardness.macosmsapp.screen.common

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import com.beardness.macosmsapp.ui.theme.dimen.Dimen
import com.beardness.macosmsapp.ui.theme.shape.Shape
import com.beardness.macosmsapp.R

@Composable
fun SmsPermissionScreen(
    onClick: () -> Unit,
) {
    val smsPermissionText = stringResource(id = R.string.sms_permission)
    val requestText = stringResource(id = R.string.request)

    Box(
        modifier = Modifier
            .fillMaxSize(),
        contentAlignment = Alignment.Center,
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Text(
                text = smsPermissionText,
                color = MaterialTheme.colorScheme.onBackground,
            )

            Spacer(
                modifier = Modifier
                    .height(
                        height = Dimen.dp16,
                    ),
            )

            Box(
                modifier = Modifier
                    .clip(shape = Shape.circle)
                    .background(color = MaterialTheme.colorScheme.primary)
                    .padding(
                        horizontal = Dimen.dp32,
                        vertical = Dimen.dp16,
                    )
                    .clickable { onClick() },
                contentAlignment = Alignment.Center,
            ) {
                Text(
                    text = requestText,
                    color = MaterialTheme.colorScheme.onPrimary,
                )
            }
        }
    }
}
