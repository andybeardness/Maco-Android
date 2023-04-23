package com.beardness.macosmsapp.usecase.flow.smstranslates

import com.beardness.macosmsapp.usecase.flow.smstranslates.dto.SmsTranslateDto
import kotlinx.coroutines.flow.Flow

interface SmsTranslatesFlowProtocol {
    val flow: Flow<List<SmsTranslateDto>>
}
