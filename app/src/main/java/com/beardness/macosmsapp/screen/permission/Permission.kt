package com.beardness.macosmsapp.screen.permission

import androidx.compose.runtime.Composable
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.isGranted
import com.google.accompanist.permissions.rememberPermissionState

@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun Permission(
    permission: String,
    granted: @Composable () -> Unit,
    denied: @Composable (request: () -> Unit) -> Unit,
) {
    val permissionState = rememberPermissionState(permission = permission)

    if (permissionState.status.isGranted) {
        granted()
    } else {
        denied(
            request = { permissionState.launchPermissionRequest() }
        )
    }
}