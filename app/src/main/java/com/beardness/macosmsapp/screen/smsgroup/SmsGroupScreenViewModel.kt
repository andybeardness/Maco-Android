package com.beardness.macosmsapp.screen.smsgroup

import androidx.lifecycle.ViewModel
import com.beardness.macosmsapp.di.qualifiers.IoCoroutineScope
import com.beardness.macosmsapp.screen.smsgroup.dto.SmsGroupViewDto
import com.beardness.macosmsapp.usecase.flow.internet.InternetFlowProtocol
import com.beardness.macosmsapp.usecase.flow.internet.type.InternetStatus
import com.beardness.macosmsapp.usecase.flow.smsgroup.SmsGroupFlowProtocol
import com.beardness.macosmsapp.usecase.usecase.haptic.HapticUseCaseProtocol
import com.beardness.macosmsapp.usecase.usecase.updatesmsflow.UpdateSmsUseCaseProtocol
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SmsGroupScreenViewModel @Inject constructor(
    private val smsGroupFlow: SmsGroupFlowProtocol,
    private val internetFlow: InternetFlowProtocol,
    private val updateSmsFlowUseCase: UpdateSmsUseCaseProtocol,
    private val hapticUseCase: HapticUseCaseProtocol,
    @IoCoroutineScope private val ioCoroutineScope: CoroutineScope,
) : ViewModel(), SmsGroupScreenViewModelProtocol {

    init {
        refreshSmsList()
    }

    override val sms =
        smsGroupFlow
            .smsGroups
            .map { collection ->
                collection
                    .map { smsGroupDto ->
                        SmsGroupViewDto(
                            author = smsGroupDto.author,
                            body = smsGroupDto.body,
                            date = smsGroupDto.date,
                            time = smsGroupDto.time,
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

    override fun haptic() {
        ioCoroutineScope.launch {
            hapticUseCase.haptic()
        }
    }
}