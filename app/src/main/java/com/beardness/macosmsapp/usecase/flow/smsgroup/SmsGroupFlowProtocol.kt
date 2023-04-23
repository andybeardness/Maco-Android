package com.beardness.macosmsapp.usecase.flow.smsgroup

import com.beardness.macosmsapp.usecase.flow.smsgroup.dto.SmsGroupDto
import kotlinx.coroutines.flow.Flow

interface SmsGroupFlowProtocol {

    val smsGroups: Flow<List<SmsGroupDto>>

}