package com.beardness.macosmsapp.ui.component.authoravatar

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.AccountCircle
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import com.beardness.macosmsapp.ui.theme.dimen.Dimens
import com.beardness.macosmsapp.ui.theme.shape.CustomShapes

@Composable
fun AuthorAvatarComponent(
    avatarColor: Color,
    boxSize: Dp = Dimens.dp32,
    imageSize: Dp = Dimens.dp24,
) {
    Box(
        modifier = Modifier
            .size(size = boxSize)
            .background(
                color = avatarColor,
                shape = CustomShapes.circle,
            ),
        contentAlignment = Alignment.Center,
    ) {
        Image(
            modifier = Modifier
                .size(size = imageSize),
            imageVector = Icons.Rounded.AccountCircle,
            contentDescription = null,
            colorFilter = ColorFilter.tint(color = Color.White.copy(alpha = .3f))
        )
    }
}

@Composable
@Preview
fun Preview_AuthorAvatarComponent_Red() {
    AuthorAvatarComponent(avatarColor = Color.Red)
}

@Composable
@Preview
fun Preview_AuthorAvatarComponent_Green() {
    AuthorAvatarComponent(avatarColor = Color.Green)
}

@Composable
@Preview
fun Preview_AuthorAvatarComponent_Blue() {
    AuthorAvatarComponent(avatarColor = Color.Blue)
}