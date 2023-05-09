package com.beardness.macosmsapp.ui.theme.material

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Shapes
import com.beardness.macosmsapp.ui.theme.additional.MacoDimens

val MacoShapes = Shapes(
    extraSmall = RoundedCornerShape(percent = 50),
    small = RoundedCornerShape(size = MacoDimens.dp8),
    medium = RoundedCornerShape(size = MacoDimens.dp12),
    large = RoundedCornerShape(size = MacoDimens.dp16),
    extraLarge = RoundedCornerShape(size = MacoDimens.dp0),
)