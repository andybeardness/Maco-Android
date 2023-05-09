package com.beardness.macosmsapp.ui.compose.component.button

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.ImageVector
import com.beardness.macosmsapp.ui.theme.dimen.Dimens
import com.beardness.macosmsapp.ui.theme.shape.CustomShapes

@Composable
fun ButtonComponent(
    icon: ImageVector,
    onClick: () -> Unit,
) {
    Box(
        modifier = Modifier
            .size(size = Dimens.dp56)
            .clip(shape = CustomShapes.circle)
            .clickable(onClick = onClick)
            .background(color = MaterialTheme.colorScheme.primary),
        contentAlignment = Alignment.Center,
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