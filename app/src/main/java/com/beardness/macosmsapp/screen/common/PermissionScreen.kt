package com.beardness.macosmsapp.screen.common

import androidx.compose.runtime.Composable
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.isGranted
import com.google.accompanist.permissions.rememberPermissionState

@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun PermissionScreen(
    permission: String,
    granted: @Composable () -> Unit,
    denied: @Composable (request: () -> Unit) -> Unit,
    whenGranted: () -> Unit = {},
) {
    val smsPermissionState = rememberPermissionState(permission = permission)

    when {
        smsPermissionState.status.isGranted -> { whenGranted() }
    }

    if (smsPermissionState.status.isGranted) {
        granted()
    } else {
        denied(request = { smsPermissionState.launchPermissionRequest() })
    }
}