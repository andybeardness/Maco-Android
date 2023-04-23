package com.beardness.macosmsapp.ui.widget.smsbygrouplist

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.beardness.macosmsapp.screen.smsbygroup.dto.GroupSmsViewDto
import com.beardness.macosmsapp.ui.component.sms.group.SmsGroup

@Composable
fun SmsByGroupListWidget(
    sms: List<GroupSmsViewDto>,
    onClickAuthor: (author: String) -> Unit,
) {
    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        items(count = sms.size) { index ->
            sms.getOrNull(index = index)?.let { current ->
                SmsGroup(
                    avatarColor = current.avatarColor,
                    author = current.author,
                    body = current.body,
                    date = current.date,
                    time = current.time,
                    onClick = { onClickAuthor(current.author) }
                )
            }
        }
    }
}