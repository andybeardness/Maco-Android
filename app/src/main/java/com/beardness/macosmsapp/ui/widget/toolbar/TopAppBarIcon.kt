package com.beardness.macosmsapp.ui.widget.toolbar

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import com.beardness.macosmsapp.extensions.clickableIfNotNull
import com.beardness.macosmsapp.ui.theme.dimen.Dimens
import com.beardness.macosmsapp.ui.theme.shape.CustomShapes

@Composable
fun TopAppBarIcon(
    imageVector: ImageVector,
    tint: Color,
    onClick: (() -> Unit)?,
) {
    Box(
        modifier = Modifier
            .size(size = Dimens.dp48)
            .padding(all = Dimens.dp6)
            .clip(shape = CustomShapes.circle)
            .clickableIfNotNull(onClick = onClick),
        contentAlignment = Alignment.Center,
    ) {
        Icon(
            modifier = Modifier
                .size(size = Dimens.dp24),
            imageVector = imageVector,
            contentDescription = null,
            tint = tint,
        )
    }
}
