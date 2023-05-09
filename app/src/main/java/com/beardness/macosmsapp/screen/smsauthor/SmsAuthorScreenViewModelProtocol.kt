package com.beardness.macosmsapp.screen.smsauthor

import com.beardness.macosmsapp.screen.smsauthor.dto.SmsAuthorViewDto
import com.beardness.macosmsapp.usecase.flow.internet.type.InternetStatus
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.StateFlow

interface SmsAuthorScreenViewModelProtocol {
    val authorFlow: StateFlow<String>
    val smsByAuthorFlow: Flow<List<SmsAuthorViewDto>>
    val internetConnectionFlow: Flow<InternetStatus>
    val smsProcessingCollectionFlow: Flow<Set<Int>>

    fun setup(author: String)
    fun translate(smsId: Int)
    fun copyToClipboard(text: String)
    fun haptic()
}