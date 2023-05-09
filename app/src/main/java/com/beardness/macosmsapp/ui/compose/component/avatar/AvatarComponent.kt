package com.beardness.macosmsapp.ui.compose.component.avatar

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import com.beardness.macosmsapp.ui.theme.additional.MacoDimens

@Composable
fun AvatarComponent(color: Color) {
    Box(
        modifier = Modifier
            .size(size = MacoDimens.dp40)
            .clip(shape = MaterialTheme.shapes.extraSmall)
            .background(color = color),
        contentAlignment = Alignment.Center,
    ) {
        AvatarIconComponent(color = Color.White.copy(alpha = .8f))
    }
}