package com.beardness.macosmsapp.ui.compose.widget.smstranslateslist

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.beardness.macosmsapp.screen.smsauthor.dto.SmsAuthorViewDto
import com.beardness.macosmsapp.ui.compose.component.sms.SmsComponent

@Composable
fun SmsTranslatesListWidget(
    smsCollection: List<SmsAuthorViewDto>,
    onClickTranslate: (smsId: Int) -> Unit,
    smsProcessing: Set<Int>,
    onClickTranslatedText: (text: String) -> Unit,
) {
    LazyColumn(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        items(items = smsCollection) {smsViewDto ->
            val isSmsProcessing = smsProcessing.contains(element = smsViewDto.id)

            SmsComponent(
                sms = smsViewDto,
                onClickBody = { onClickTranslate(smsViewDto.id) },
                processing = isSmsProcessing,
                onClickText = onClickTranslatedText,
            )
        }
    }
}