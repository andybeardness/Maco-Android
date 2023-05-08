package com.beardness.macosmsapp.ui.widget.toolbar.smsbygroup

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.beardness.macosmsapp.ui.component.toolbar.refresh.ToolbarRefreshButton
import com.beardness.macosmsapp.ui.component.toolbar.title.ToolbarTitleComponent
import com.beardness.macosmsapp.ui.theme.dimen.Dimens

@Composable
fun ToolbarSmsByGroupWidget(
    title: String,
    onClickRefresh: () -> Unit,
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(height = Dimens.dp64)
            .background(color = MaterialTheme.colorScheme.background)
            .padding(horizontal = Dimens.dp16),
        verticalAlignment = Alignment.CenterVertically
    ) {
        ToolbarTitleComponent(
            modifier = Modifier.weight(weight = 1f),
            title = title,
            titleColor = MaterialTheme.colorScheme.onBackground,
        )

        ToolbarRefreshButton(
            color = MaterialTheme.colorScheme.onBackground,
            onClick = onClickRefresh,
        )
    }
}