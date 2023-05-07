package com.beardness.macosmsapp.ui.component.sms.group

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.beardness.macosmsapp.ui.component.authoravatar.AuthorAvatarComponent
import com.beardness.macosmsapp.ui.theme.dimen.Dimen

private const val BODY_MAX_LINES = 3

@Composable
fun SmsGroup(
    avatarColor: Color,
    author: String,
    body: String,
    date: String,
    time: String,
    onClick: () -> Unit,
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .defaultMinSize(minHeight = Dimen.dp96)
            .clickable { onClick() }
            .padding(
                vertical = Dimen.dp16,
                horizontal = Dimen.dp16,
            ),
    ) {
        AvatarBlock(
            avatarColor = avatarColor,
        )

        ContentBlock(
            author = author,
            body = body,
            date = date,
            time = time,
        )
    }
}

@Composable
private fun AvatarBlock(
    avatarColor: Color
) {
    Box(
        contentAlignment = Alignment.TopCenter,
    ) {
        AuthorAvatarComponent(avatarColor = avatarColor)
    }

    Spacer(
        modifier = Modifier
            .width(width = Dimen.dp16),
    )
}

@Composable
private fun ContentBlock(
    author: String,
    body: String,
    date: String,
    time: String,
) {
    Column {
        AuthorDateTimeBlock(
            author = author,
            date = date,
            time = time,
        )

        BodyBlock(
            body = body,
        )
    }
}

@Composable
private fun AuthorDateTimeBlock(
    author: String,
    date: String,
    time: String,
) {
    Row(
        modifier = Modifier
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Text(
            modifier = Modifier
                .weight(weight = 1f),
            text = author,
            fontSize = 16.sp,
            fontWeight = FontWeight.SemiBold,
            color = MaterialTheme.colorScheme.onBackground,
        )

        Text(
            modifier = Modifier,
            text = "$date $time",
            fontSize = 14.sp,
            fontWeight = FontWeight.Thin,
            color = MaterialTheme.colorScheme.onBackground.copy(alpha = .5f),
        )
    }
}

@Composable
private fun BodyBlock(
    body: String,
) {
    Text(
        text = body,
        maxLines = BODY_MAX_LINES,
        overflow = TextOverflow.Ellipsis,
        fontSize = 16.sp,
        color = MaterialTheme.colorScheme.onBackground,
        fontWeight = FontWeight.Light,
    )
}


@Preview
@Composable
fun Preview_GroupSms() {
    SmsGroup(
        avatarColor = Color.Green,
        author = "TBC SMS",
        body = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor " +
                "incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis " +
                "nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. " +
                "Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore " +
                "eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt " +
                "in culpa qui officia deserunt mollit anim id est laborum.",
        date = "2022.12.22",
        time = "14:41",
        onClick = {},
    )
}