package com.beardness.macosmsapp.ui.compose.component.fab

import androidx.compose.foundation.layout.size
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.FloatingActionButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import com.beardness.macosmsapp.ui.theme.additional.MacoDimens

@Composable
fun Fab(
    modifier: Modifier,
    icon: ImageVector,
    action: () -> Unit,
) {
    FloatingActionButton(
        modifier = modifier,
        onClick = action,
        containerColor = MaterialTheme.colorScheme.primary,
        elevation = FloatingActionButtonDefaults.elevation(
            defaultElevation = MacoDimens.dp0,
            pressedElevation = MacoDimens.dp0,
            focusedElevation = MacoDimens.dp0,
            hoveredElevation = MacoDimens.dp0,
        )
    ) {
        Icon(
            modifier = Modifier
                .size(size = MacoDimens.dp24),
            imageVector = icon,
            contentDescription = null,
            tint = MaterialTheme.colorScheme.onPrimary,
        )
    }
}