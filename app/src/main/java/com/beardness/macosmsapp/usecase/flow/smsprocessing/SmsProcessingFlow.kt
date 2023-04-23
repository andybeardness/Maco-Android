package com.beardness.macosmsapp.usecase.flow.smsprocessing

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

class SmsProcessingFlow @Inject constructor(
) : SmsProcessingFlowProtocol {

    private val _flow = MutableStateFlow<Set<Int>>(value = emptySet())
    override val flowSmsIdWithProcessing: Flow<Set<Int>> = _flow.asStateFlow()

    override suspend fun push(smsId: Int) {
        _flow.emit(value = _flow.value.toMutableSet().apply { add(element = smsId) })
    }

    override suspend fun pop(smsId: Int) {
        _flow.emit(value = _flow.value.toMutableSet().apply { remove(element = smsId) })
    }
}