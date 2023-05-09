package com.beardness.macosmsapp.ui.compose.component.fab

import androidx.compose.foundation.layout.size
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.FloatingActionButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import com.beardness.macosmsapp.ui.theme.dimen.Dimens

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
            defaultElevation = Dimens.dp0,
            pressedElevation = Dimens.dp0,
            focusedElevation = Dimens.dp0,
            hoveredElevation = Dimens.dp0,
        )
    ) {
        Icon(
            modifier = Modifier
                .size(size = Dimens.dp24),
            imageVector = icon,
            contentDescription = null,
            tint = MaterialTheme.colorScheme.onPrimary,
        )
    }
}