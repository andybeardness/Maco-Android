package com.beardness.macosmsapp.ui.compose.sms

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.beardness.macosmsapp.screen.smsbyauthor.dto.SmsViewDto
import com.beardness.macosmsapp.ui.theme.dimen.Dimens

@Composable
fun SmsComponent(
    sms: SmsViewDto,
    onClickBody: () -> Unit,
    processing: Boolean,
    onClickText: (text: String) -> Unit,
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = Dimens.dp8),
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