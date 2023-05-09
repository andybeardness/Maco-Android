package com.beardness.macosmsapp.ui.compose.component.icon

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import com.beardness.macosmsapp.extensions.clickableIfNotNull
import com.beardness.macosmsapp.ui.theme.additional.MacoDimens

@Composable
fun ToolbarIconComponent(
    imageVector: ImageVector,
    tint: Color,
    onClick: (() -> Unit)?,
) {
    Box(
        modifier = Modifier
            .size(size = MacoDimens.dp48)
            .padding(all = MacoDimens.dp0)
            .clip(shape = MaterialTheme.shapes.extraSmall)
            .clickableIfNotNull(onClick = onClick),
        contentAlignment = Alignment.Center,
    ) {
        Icon(
            modifier = Modifier
                .size(size = MacoDimens.dp28),
            imageVector = imageVector,
            contentDescription = null,
            tint = tint,
        )
    }
}
