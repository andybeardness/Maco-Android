package com.beardness.macosmsapp.screen.smsauthor

import androidx.lifecycle.ViewModel
import com.beardness.macosmsapp.di.qualifiers.IoCoroutineScope
import com.beardness.macosmsapp.screen.smsauthor.dto.SmsTranslateViewDto
import com.beardness.macosmsapp.screen.smsauthor.dto.SmsAuthorViewDto
import com.beardness.macosmsapp.usecase.clipboard.ClipboardUseCaseProtocol
import com.beardness.macosmsapp.usecase.common.helpers.datetime.DateTimeFormatterProtocol
import com.beardness.macosmsapp.usecase.flow.internet.InternetFlowProtocol
import com.beardness.macosmsapp.usecase.flow.internet.type.InternetStatus
import com.beardness.macosmsapp.usecase.flow.smsprocessing.SmsProcessingFlowProtocol
import com.beardness.macosmsapp.usecase.flow.smstranslates.SmsTranslatesFlowProtocol
import com.beardness.macosmsapp.usecase.flow.smstranslates.dto.SmsTranslateDto
import com.beardness.macosmsapp.usecase.usecase.haptic.HapticUseCaseProtocol
import com.beardness.macosmsapp.usecase.usecase.translatesms.TranslateSmsUseCaseProtocol
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SmsAuthorScreenViewModel @Inject constructor(
    private val translateSmsUseCase: TranslateSmsUseCaseProtocol,
    private val clipboardUseCase: ClipboardUseCaseProtocol,
    private val hapticUseCase: HapticUseCaseProtocol,
    private val smsTranslatesFlow: SmsTranslatesFlowProtocol,
    private val internetFlow: InternetFlowProtocol,
    private val smsProcessingFlow: SmsProcessingFlowProtocol,
    private val dateTimeManager: DateTimeFormatterProtocol,
    @IoCoroutineScope private val ioCoroutineScope: CoroutineScope,
) : ViewModel(), SmsAuthorScreenViewModelProtocol {

    private val _authorFlow: MutableStateFlow<String> = MutableStateFlow(value = "")
    override val authorFlow = _authorFlow.asStateFlow()

    override val smsByAuthorFlow: Flow<List<SmsAuthorViewDto>> =
        combine(
            smsTranslatesFlow.flow,
            authorFlow,
        ) { sms,
            author ->

            sms.filter { smsTranslateDto -> smsTranslateDto.author == author}
                .sortedByDescending { smsTranslateDto -> smsTranslateDto.date }
                .map { smsTranslateDto -> smsTranslateDto.viewDto() }
      }

    override val internetConnectionFlow: Flow<InternetStatus> =
        internetFlow.flow

    override val smsProcessingCollectionFlow: Flow<Set<Int>> =
        smsProcessingFlow.flowSmsIdWithProcessing

    override fun setup(
        author: String,
    ) {
        ioCoroutineScope.launch {
            _authorFlow.emit(value = author)
        }
    }

    override fun translate(
        smsId: Int,
    ) {
        ioCoroutineScope.launch {
            smsProcessingFlow.push(smsId = smsId)

            translateSmsUseCase.translate(
                smsId = smsId,
            )

            smsProcessingFlow.pop(smsId = smsId)
        }
    }

    override fun copyToClipboard(text: String) {
        ioCoroutineScope.launch {
            clipboardUseCase.copyToClipboard(text = text)
        }
    }

    override fun haptic() {
        ioCoroutineScope.launch {
            hapticUseCase.haptic()
        }
    }

    private fun SmsTranslateDto.viewDto(): SmsAuthorViewDto {
        val datetime = dateTimeManager.format(
            datetime = this.date,
        )

        return SmsAuthorViewDto(
            id = this.id,
            author = this.author,
            body = this.body,
            date = datetime.date,
            time = datetime.time,
            translates = SmsTranslateViewDto(
                georgian = this.translates.georgian,
                auto = this.translates.auto,
            )
        )
    }
}

