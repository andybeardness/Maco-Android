package com.beardness.macosmsapp.ui.compose.component.sms

import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import com.beardness.macosmsapp.ui.theme.dimen.Dimens

@Composable
fun SmsIconComponent(
    modifier: Modifier,
    imageVector: ImageVector,
) {
    Icon(
        modifier = modifier
            .size(size = Dimens.dp24),
        imageVector = imageVector,
        contentDescription = null,
        tint = MaterialTheme.colorScheme.onSurface.copy(alpha = .1f)
    )
}