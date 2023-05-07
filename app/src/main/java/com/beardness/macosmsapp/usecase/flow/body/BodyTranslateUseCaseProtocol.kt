package com.beardness.macosmsapp.usecase.flow.body

import com.beardness.macosmsapp.usecase.flow.body.dto.BodyTranslatedDto
import kotlinx.coroutines.flow.StateFlow

interface BodyTranslateUseCaseProtocol {
    val entered: StateFlow<String>
    val translated: StateFlow<BodyTranslatedDto?>
    val inProgress: StateFlow<Boolean>

    suspend fun updateEntered(text: String)
    suspend fun translate(text: String)
}
