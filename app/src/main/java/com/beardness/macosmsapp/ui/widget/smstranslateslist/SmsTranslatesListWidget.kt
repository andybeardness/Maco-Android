package com.beardness.macosmsapp.ui.widget.smstranslateslist

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.beardness.macosmsapp.screen.smsbyauthor.dto.SmsViewDto
import com.beardness.macosmsapp.ui.component.sms.translate.SmsTranslateComponent

@Composable
fun SmsTranslatesWidget(
    smsCollection: List<SmsViewDto>,
    onClickAuto: (smsId: Int) -> Unit,
    smsProcessing: Set<Int>,
) {
    LazyColumn(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        items(items = smsCollection) {smsViewDto ->
            val isSmsProcessing = smsProcessing.contains(element = smsViewDto.id)

            SmsTranslateComponent(
                sms = smsViewDto,
                onClickTranslate = { onClickAuto(smsViewDto.id) },
                isSmsProcessing = isSmsProcessing,
            )
        }
    }
}