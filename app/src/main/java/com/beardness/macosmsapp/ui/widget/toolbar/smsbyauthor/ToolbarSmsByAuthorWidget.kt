package com.beardness.macosmsapp.ui.widget.toolbar.smsbyauthor

import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.beardness.macosmsapp.ui.component.authoravatar.AuthorAvatarComponent
import com.beardness.macosmsapp.ui.component.toolbar.title.ToolbarTitleComponent
import com.beardness.macosmsapp.ui.theme.dimen.Dimens

@Composable
fun ToolbarSmsByAuthorWidget(
    avatarColor: Color,
    author: String,
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(height = Dimens.dp64)
            .padding(horizontal = Dimens.dp16),
        verticalAlignment = Alignment.CenterVertically
    ) {
        AuthorAvatarComponent(
            avatarColor = avatarColor,
        )

        Spacer(
            modifier = Modifier
                .width(
                    width = Dimens.dp16,
                ),
        )

        ToolbarTitleComponent(
            modifier = Modifier
                .weight(weight = 1f),
            title = author,
            titleColor = MaterialTheme.colorScheme.onBackground,
        )
    }
}