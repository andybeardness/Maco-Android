package com.beardness.macosmsapp.ui.compose.component.sms

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.beardness.macosmsapp.screen.smsauthor.dto.SmsAuthorViewDto
import com.beardness.macosmsapp.ui.theme.additional.MacoDimens

@Composable
fun SmsComponent(
    sms: SmsAuthorViewDto,
    onClickBody: () -> Unit,
    processing: Boolean,
    onClickText: (text: String) -> Unit,
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = MacoDimens.dp8),
    ) {
        SmsDateTimeComponent(
            date = sms.date,
            time = sms.time,
        )

        SmsCardComponent(
            sms = sms,
            processing = processing,
            onClickBody = onClickBody,
            onClickText = onClickText,
        )
    }
}