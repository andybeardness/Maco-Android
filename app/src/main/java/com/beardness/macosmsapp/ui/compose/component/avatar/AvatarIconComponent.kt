package com.beardness.macosmsapp.ui.compose.component.avatar

import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.AlternateEmail
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.beardness.macosmsapp.ui.theme.additional.MacoDimens

@Composable
fun AvatarIconComponent(color: Color) {
    Icon(
        modifier = Modifier
            .size(size = MacoDimens.dp24)
            .offset(
                x = MacoDimens.dp2,
                y = MacoDimens.dp2,
            ),
        imageVector = Icons.Rounded.AlternateEmail,
        contentDescription = null,
        tint = Color.Black.copy(alpha = .1f)
    )
    Icon(
        modifier = Modifier
            .size(size = MacoDimens.dp24),
        imageVector = Icons.Rounded.AlternateEmail,
        contentDescription = null,
        tint = color
    )
}