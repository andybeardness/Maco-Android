package com.beardness.macosmsapp.screen.smsbygroup

import androidx.lifecycle.ViewModel
import com.beardness.macosmsapp.di.qualifiers.IoCoroutineScope
import com.beardness.macosmsapp.screen.smsbygroup.dto.GroupSmsViewDto
import com.beardness.macosmsapp.usecase.flow.internet.InternetFlowProtocol
import com.beardness.macosmsapp.usecase.flow.internet.type.InternetStatus
import com.beardness.macosmsapp.usecase.flow.smsgroup.SmsGroupFlowProtocol
import com.beardness.macosmsapp.usecase.usecase.updatesmsflow.UpdateSmsUseCaseProtocol
import com.beardness.macosmsapp.utils.SpecificChars
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SmsByGroupScreenViewModel @Inject constructor(
    private val smsGroupFlow: SmsGroupFlowProtocol,
    private val internetFlow: InternetFlowProtocol,
    private val updateSmsFlowUseCase: UpdateSmsUseCaseProtocol,
    @IoCoroutineScope private val ioCoroutineScope: CoroutineScope,
) : ViewModel(), SmsByGroupScreenViewModelProtocol {

    override val sms =
        smsGroupFlow
            .smsGroups
            .map { collection ->
                collection
                    .map { smsGroupDto ->
                        GroupSmsViewDto(
                            author = smsGroupDto.author,
                            body = smsGroupDto.body,
                            date = smsGroupDto.date,
                            time = smsGroupDto.time,
                            avatarColor = smsGroupDto.avatarColor,
                        )
                    }
            }

    override val internet: Flow<Boolean> =
        internetFlow.flow.map { status ->
            when (status) {
                InternetStatus.Available -> true
                InternetStatus.Lost -> false
            }
        }


    override fun refreshSmsList() {
        ioCoroutineScope.launch {
            updateSmsFlowUseCase.update()
        }
    }
}