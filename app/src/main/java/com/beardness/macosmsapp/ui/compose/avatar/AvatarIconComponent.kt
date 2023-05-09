package com.beardness.macosmsapp.ui.compose.avatar

import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.AlternateEmail
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.beardness.macosmsapp.ui.theme.dimen.Dimens

@Composable
fun AvatarIconComponent(color: Color) {
    Icon(
        modifier = Modifier
            .size(size = Dimens.dp24)
            .offset(
                x = Dimens.dp2,
                y = Dimens.dp2,
            ),
        imageVector = Icons.Rounded.AlternateEmail,
        contentDescription = null,
        tint = Color.Black.copy(alpha = .2f)
    )
    Icon(
        modifier = Modifier
            .size(size = Dimens.dp24),
        imageVector = Icons.Rounded.AlternateEmail,
        contentDescription = null,
        tint = color
    )
}