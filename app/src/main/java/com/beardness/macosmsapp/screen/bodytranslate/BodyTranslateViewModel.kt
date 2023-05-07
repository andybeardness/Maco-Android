package com.beardness.macosmsapp.screen.bodytranslate

import androidx.lifecycle.ViewModel
import com.beardness.macosmsapp.di.qualifiers.IoCoroutineScope
import com.beardness.macosmsapp.screen.bodytranslate.dto.BodyTranslatedViewDto
import com.beardness.macosmsapp.usecase.clipboard.ClipboardUseCaseProtocol
import com.beardness.macosmsapp.usecase.flow.body.BodyTranslateUseCaseProtocol
import com.beardness.macosmsapp.usecase.flow.internet.InternetFlowProtocol
import com.beardness.macosmsapp.usecase.flow.internet.type.InternetStatus
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BodyTranslateViewModel @Inject constructor(
    private val bodyTranslateUseCase: BodyTranslateUseCaseProtocol,
    private val clipboardUseCase: ClipboardUseCaseProtocol,
    private val internetFlow: InternetFlowProtocol,
    @IoCoroutineScope private val ioCoroutineScope: CoroutineScope,
) : ViewModel(), BodyTranslateViewModelProtocol {

    override val translated: Flow<BodyTranslatedViewDto?> =
        bodyTranslateUseCase
            .translated
            .map { bodyTranslatedDto ->
                bodyTranslatedDto ?: return@map null

                BodyTranslatedViewDto(
                    translatedAuto = bodyTranslatedDto.translatedAuto,
                    translatedGe = bodyTranslatedDto.translatedGe,
                )
            }

    override val inProgress: Flow<Boolean> =
        bodyTranslateUseCase
            .inProgress

    override val internet: Flow<Boolean> =
        internetFlow
            .flow
            .map { internetStatus ->
                when (internetStatus) {
                    InternetStatus.Available -> true
                    else -> false
                }
            }

    private val input: StateFlow<String> = bodyTranslateUseCase.entered

    override fun input(updated: String) {
        ioCoroutineScope.launch {
            bodyTranslateUseCase.updateEntered(text = updated)
        }
    }

    override fun input(): String = input.value

    override fun translate(text: String) {
        ioCoroutineScope.launch {
            bodyTranslateUseCase.translate(text = text)
        }
    }

    override fun copyToClipboard(text: String) {
        ioCoroutineScope.launch {
            clipboardUseCase.copyToClipboard(text = text)
        }
    }
}