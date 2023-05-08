package com.beardness.macosmsapp.ui.component.sms

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import com.beardness.macosmsapp.ui.theme.dimen.Dimens
import com.beardness.macosmsapp.ui.theme.shape.CustomShapes

@Composable
fun AvatarComponent(color: Color) {
    Box(
        modifier = Modifier
            .size(size = Dimens.dp40)
            .clip(shape = CustomShapes.circle)
            .background(color = color),
        contentAlignment = Alignment.Center,
    ) {
        AvatarIconComponent(color = Color.White.copy(alpha = .8f))
    }
}