package com.beardness.macosmsapp.usecase.flow.body

import com.beardness.macosmsapp.usecase.flow.body.dto.TranslateState
import kotlinx.coroutines.flow.StateFlow

interface BodyTranslateUseCaseProtocol {
    val entered: StateFlow<String>
    val translated: StateFlow<TranslateState>
    val inProgress: StateFlow<Boolean>

    suspend fun updateEntered(text: String)
    suspend fun translate(text: String)
    suspend fun clearTranslate()
}
