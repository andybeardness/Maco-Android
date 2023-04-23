package com.beardness.macosmsapp.usecase.flow.smsprocessing

import kotlinx.coroutines.flow.Flow

interface SmsProcessingFlowProtocol {
    val flowSmsIdWithProcessing: Flow<Set<Int>>

    suspend fun push(smsId: Int)
    suspend fun pop(smsId: Int)
}
