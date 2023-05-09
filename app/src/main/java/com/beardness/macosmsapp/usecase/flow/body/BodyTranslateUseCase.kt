package com.beardness.macosmsapp.usecase.flow.body

import com.beardness.macosmsapp.di.qualifiers.QAutoTranslator
import com.beardness.macosmsapp.di.qualifiers.QGeTranslator
import com.beardness.macosmsapp.usecase.common.translator.BaseTranslatorProtocol
import com.beardness.macosmsapp.usecase.flow.body.dto.BodyTranslatedDto
import com.beardness.macosmsapp.usecase.flow.body.dto.TranslateState
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.*
import javax.inject.Inject

class BodyTranslateUseCase @Inject constructor(
    @QAutoTranslator private val autoTranslator: BaseTranslatorProtocol,
    @QGeTranslator private val geTranslator: BaseTranslatorProtocol,
) : BodyTranslateUseCaseProtocol {

    private val _entered = MutableStateFlow<String>(value = "")
    override val entered: StateFlow<String> = _entered.asStateFlow()

    private val _translated = MutableStateFlow<TranslateState>(value = TranslateState.NoTranslate)
    override val translated: StateFlow<TranslateState> = _translated.asStateFlow()

    private val _inProgress = MutableStateFlow<Boolean>(value = false)
    override val inProgress: StateFlow<Boolean> = _inProgress.asStateFlow()

    override suspend fun updateEntered(text: String) {
        _entered.emit(value = text)
    }

    override suspend fun translate(text: String) {
        text.ifBlank { return }

        updateProgress(status = true)

        val currentScope = CoroutineScope(context = Dispatchers.Default)

        val translatedAuto = currentScope.async { autoTranslator.translate(text = text) ?: "" }
        val translatedGe = currentScope.async { geTranslator.translate(text = text) ?: "" }

        updateTranslate(
            translatedAuto = translatedAuto.await(),
            translatedGe = translatedGe.await(),
        )

        updateProgress(status = false)
    }

    override suspend fun clearTranslate() {
        _translated.emit(value = TranslateState.NoTranslate)
    }

    private suspend fun updateProgress(status: Boolean) {
        _inProgress.emit(value = status)
    }

    private suspend fun updateTranslate(
        translatedAuto: String,
        translatedGe: String,
    ) {
        _translated.emit(
            value = TranslateState.Translate(
                translation = BodyTranslatedDto(
                    translatedAuto = translatedAuto,
                    translatedGe = translatedGe,
                )
            )
        )
    }
}