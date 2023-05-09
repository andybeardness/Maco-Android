package com.beardness.macosmsapp.ui.compose.component.sms.group

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import com.beardness.macosmsapp.ui.compose.component.avatar.AvatarComponent
import com.beardness.macosmsapp.ui.compose.component.spacer.SpacerColumn
import com.beardness.macosmsapp.ui.compose.component.spacer.SpacerRow
import com.beardness.macosmsapp.ui.theme.additional.MacoDimens

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
                .clickable(onClick = onClick)
                .padding(
                    top = MacoDimens.dp12,
                    bottom = MacoDimens.dp12,
                    start = MacoDimens.dp16,
                    end = MacoDimens.dp24,
                ),
        ) {
            AvatarComponent(color = avatarColor)

            SpacerRow(dp = MacoDimens.dp16)

            Column(
                modifier = Modifier
                    .weight(weight = 1f),
            ) {
                Text(
                    text = author,
                    style = MaterialTheme.typography.headlineMedium,
                )
                
                SpacerColumn(dp = MacoDimens.dp4)
                
                Text(
                    text = body,
                    style = MaterialTheme.typography.bodyMedium,
                    maxLines = 3,
                    overflow = TextOverflow.Ellipsis,
                )
            }
        }

        Spacer(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = MacoDimens.dp16)
                .height(height = MacoDimens.dp1)
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