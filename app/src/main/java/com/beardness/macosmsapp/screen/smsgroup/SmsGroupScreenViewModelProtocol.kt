package com.beardness.macosmsapp.screen.smsgroup

import com.beardness.macosmsapp.screen.smsgroup.dto.SmsGroupViewDto
import kotlinx.coroutines.flow.Flow

interface SmsGroupScreenViewModelProtocol {
    val sms: Flow<List<SmsGroupViewDto>>
    val internet: Flow<Boolean>
    fun refreshSmsList()
    fun haptic()
}
