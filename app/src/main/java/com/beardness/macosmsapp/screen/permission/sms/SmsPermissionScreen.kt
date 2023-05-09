package com.beardness.macosmsapp.screen.permission.sms

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
import com.beardness.macosmsapp.R
import com.beardness.macosmsapp.ui.theme.dimen.Dimens
import com.beardness.macosmsapp.ui.theme.shape.CustomShapes

@Composable
fun SmsPermissionScreen(
    viewModel: SmsPermissionScreenViewModelProtocol,
    permissionDialog: () -> Unit,
) {
    val haptic: () -> Unit = {
        viewModel.haptic()
    }

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
                        height = Dimens.dp16,
                    ),
            )

            Box(
                modifier = Modifier
                    .clip(shape = CustomShapes.circle)
                    .background(color = MaterialTheme.colorScheme.primary)
                    .padding(
                        horizontal = Dimens.dp32,
                        vertical = Dimens.dp16,
                    )
                    .clickable {
                        permissionDialog()
                        haptic()
                    },
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
