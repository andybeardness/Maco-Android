package com.beardness.macosmsapp.screen.smsbygroup

import com.beardness.macosmsapp.screen.smsbygroup.dto.GroupSmsViewDto
import kotlinx.coroutines.flow.Flow

interface SmsByGroupScreenViewModelProtocol {
    val sms: Flow<List<GroupSmsViewDto>>
    val internet: Flow<Boolean>
    fun refreshSmsList()
}
