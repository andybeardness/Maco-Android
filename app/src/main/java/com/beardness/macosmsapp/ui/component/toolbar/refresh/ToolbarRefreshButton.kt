package com.beardness.macosmsapp.ui.component.toolbar.refresh

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Refresh
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import com.beardness.macosmsapp.ui.theme.dimen.Dimen
import com.beardness.macosmsapp.ui.theme.shape.Shape

@Composable
fun ToolbarRefreshButton(
    color: Color,
    onClick: () -> Unit,
) {
    Image(
        modifier = Modifier
            .clip(shape = Shape.circle)
            .clickable { onClick() }
            .padding(all = Dimen.dp8)
            .size(size = Dimen.dp32),
        imageVector = Icons.Rounded.Refresh,
        contentDescription = null,
        colorFilter = ColorFilter.tint(color = color)
    )
}