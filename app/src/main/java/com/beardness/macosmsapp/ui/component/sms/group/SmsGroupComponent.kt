package com.beardness.macosmsapp.ui.component.sms.group

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import com.beardness.macosmsapp.ui.component.sms.AvatarComponent
import com.beardness.macosmsapp.ui.component.spacer.SpacerV
import com.beardness.macosmsapp.ui.theme.dimen.Dimens
import com.beardness.macosmsapp.ui.theme.shape.CustomShapes

@Composable
fun SmsGroupComponent(
    avatarColor: Color,
    author: String,
    body: String,
    onClick: () -> Unit,
) {
    Column {
        Row(
            modifier = Modifier
                .clickable { onClick() }
                .padding(
                    top = Dimens.dp12,
                    bottom = Dimens.dp12,
                    start = Dimens.dp16,
                    end = Dimens.dp24,
                ),
        ) {
            AvatarComponent(
                modifier = Modifier
                    .size(size = Dimens.dp40)
                    .clip(shape = CustomShapes.circle)
                    .background(color = avatarColor),
            )

            SpacerV(dp = Dimens.dp16)

            Column(
                modifier = Modifier
                    .weight(weight = 1f),
            ) {
                Text(
                    text = author,
                    fontStyle = MaterialTheme.typography.headlineMedium.fontStyle,
                )
                Text(
                    text = body,
                    fontStyle = MaterialTheme.typography.bodyMedium.fontStyle,
                    maxLines = 3,
                    overflow = TextOverflow.Ellipsis,
                )
            }
        }

        Spacer(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = Dimens.dp16, end = Dimens.dp24)
                .height(height = Dimens.dp1)
                .background(color = MaterialTheme.colorScheme.onBackground.copy(alpha = .1f)),
        )
    }
}

@Preview
@Composable
fun PreviewSmsGroup() {
    SmsGroupComponent(
        avatarColor = MaterialTheme.colorScheme.primary.copy(alpha = .3f),
        author = "Hambart",
        body = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.",
        onClick = { },
    )
}