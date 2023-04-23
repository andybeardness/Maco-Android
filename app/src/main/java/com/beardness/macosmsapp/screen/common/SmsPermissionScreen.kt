package com.beardness.macosmsapp.screen.common

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import com.beardness.macosmsapp.ui.theme.dimen.Dimen
import com.beardness.macosmsapp.ui.theme.shape.Shape

@Composable
fun SmsPermissionScreen(
    onClick: () -> Unit,
) {
    Box(
        modifier = Modifier
            .fillMaxSize(),
        contentAlignment = Alignment.Center,
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Text(
                text = "SMS permission",
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
                    .background(color = MaterialTheme.colorScheme.primaryContainer)
                    .padding(
                        horizontal = Dimen.dp32,
                        vertical = Dimen.dp16,
                    )
                    .clickable { onClick() },
                contentAlignment = Alignment.Center,
            ) {
                Text(
                    text = "REQUEST",
                    color = MaterialTheme.colorScheme.onPrimaryContainer,
                )
            }
        }
    }
}
