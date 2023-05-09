package com.beardness.macosmsapp.screen.body

import com.beardness.macosmsapp.screen.body.dto.TranslateViewState
import kotlinx.coroutines.flow.Flow

interface BodyTranslateViewModelProtocol {
    val translated: Flow<TranslateViewState>
    val inProgress: Flow<Boolean>
    val internet: Flow<Boolean>

    fun input(updated: String)
    fun input(): String
    fun translate(text: String)
    fun clearTranslate()
    fun copyToClipboard(text: String)
    fun haptic()
}
