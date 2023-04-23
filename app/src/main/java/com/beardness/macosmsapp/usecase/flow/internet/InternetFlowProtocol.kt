package com.beardness.macosmsapp.usecase.flow.internet

import com.beardness.macosmsapp.usecase.flow.internet.type.InternetStatus
import kotlinx.coroutines.flow.Flow

interface InternetFlowProtocol {
    val flow: Flow<InternetStatus>
    suspend fun connect()
}
