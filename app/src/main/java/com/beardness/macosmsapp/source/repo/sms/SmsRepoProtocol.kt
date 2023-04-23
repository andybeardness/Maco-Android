package com.beardness.macosmsapp.source.repo.sms

import com.beardness.macosmsapp.source.repo.sms.dto.SmsRepoDto
import kotlinx.coroutines.flow.StateFlow

interface SmsRepoProtocol {
    suspend fun smsFromDevice(): List<SmsRepoDto>
    suspend fun smsFromDevice(smsId: Int): SmsRepoDto?
}
