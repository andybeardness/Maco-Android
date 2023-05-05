package com.beardness.macosmsapp.screen.bodytranslate

import com.beardness.macosmsapp.screen.bodytranslate.dto.BodyTranslatedViewDto
import kotlinx.coroutines.flow.Flow

interface BodyTranslateViewModelProtocol {
    val entered: Flow<String?>
    val translated: Flow<BodyTranslatedViewDto?>
    val inProgress: Flow<Boolean>
    val internet: Flow<Boolean>

    fun updateEntered(text: String)
    fun translate(text: String)
}
