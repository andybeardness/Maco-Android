package com.beardness.macosmsapp.ui.compose.fab

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Edit
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun Fab(
    modifier: Modifier,
    action: () -> Unit,
) {
    FloatingActionButton(
        modifier = modifier,
        onClick = action,
        containerColor = MaterialTheme.colorScheme.primary,
    ) {
        Icon(
            imageVector = Icons.Rounded.Edit ,
            contentDescription = null,
            tint = MaterialTheme.colorScheme.onPrimary,
        )
    }
}