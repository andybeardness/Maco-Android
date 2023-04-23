package com.beardness.macosmsapp.screen.smsbyauthor

import androidx.compose.ui.graphics.Color
import com.beardness.macosmsapp.screen.smsbyauthor.dto.SmsViewDto
import com.beardness.macosmsapp.usecase.common.types.LanguageCode
import com.beardness.macosmsapp.usecase.flow.internet.type.InternetStatus
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

interface SmsByAuthorScreenViewModelProtocol {
    val authorFlow: StateFlow<String>
    val authorAvatarColorFlow: Flow<Color>
    val smsByAuthorFlow: Flow<List<SmsViewDto>>
    val internetConnectionFlow: Flow<InternetStatus>
    val smsProcessingCollectionFlow: Flow<Set<Int>>

    fun setup(author: String)

    fun translate(smsId: Int, languageCode: LanguageCode)
}