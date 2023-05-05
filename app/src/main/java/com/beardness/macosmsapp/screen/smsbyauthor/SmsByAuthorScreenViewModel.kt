package com.beardness.macosmsapp.screen.smsbyauthor

import androidx.lifecycle.ViewModel
import com.beardness.macosmsapp.di.qualifiers.IoCoroutineScope
import com.beardness.macosmsapp.screen.smsbyauthor.dto.SmsTranslateViewDto
import com.beardness.macosmsapp.screen.smsbyauthor.dto.SmsViewDto
import com.beardness.macosmsapp.usecase.common.helpers.avatarcolorgenerator.AvatarColorGeneratorProtocol
import com.beardness.macosmsapp.usecase.common.helpers.datetime.DateTimeFormatterProtocol
import com.beardness.macosmsapp.usecase.flow.internet.InternetFlowProtocol
import com.beardness.macosmsapp.usecase.flow.internet.type.InternetStatus
import com.beardness.macosmsapp.usecase.flow.smsprocessing.SmsProcessingFlowProtocol
import com.beardness.macosmsapp.usecase.flow.smstranslates.SmsTranslatesFlowProtocol
import com.beardness.macosmsapp.usecase.flow.smstranslates.dto.SmsTranslateDto
import com.beardness.macosmsapp.usecase.usecase.translatesms.TranslateSmsUseCaseProtocol
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SmsByAuthorScreenViewModel @Inject constructor(
    private val translateSmsUseCase: TranslateSmsUseCaseProtocol,
    smsTranslatesFlow: SmsTranslatesFlowProtocol,
    internetFlow: InternetFlowProtocol,
    private val smsProcessingFlow: SmsProcessingFlowProtocol,
    private val avatarColorGenerator: AvatarColorGeneratorProtocol,
    private val dateTimeManager: DateTimeFormatterProtocol,
    @IoCoroutineScope private val ioCoroutineScope: CoroutineScope,
) : ViewModel(), SmsByAuthorScreenViewModelProtocol {

    private val _authorFlow: MutableStateFlow<String> = MutableStateFlow(value = "")
    override val authorFlow = _authorFlow.asStateFlow()

    override val authorAvatarColorFlow =
        authorFlow.map { author ->
            avatarColorGenerator
                .generate(
                    author = author,
                )
        }

    override val smsByAuthorFlow: Flow<List<SmsViewDto>> =
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

    private fun SmsTranslateDto.viewDto(): SmsViewDto {
        val datetime = dateTimeManager.format(
            datetime = this.date,
        )

        return SmsViewDto(
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

