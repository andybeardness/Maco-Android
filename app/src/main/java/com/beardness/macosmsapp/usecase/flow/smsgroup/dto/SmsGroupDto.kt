package com.beardness.macosmsapp.usecase.flow.smsgroup.dto

import androidx.compose.ui.graphics.Color

data class SmsGroupDto(
    val author: String,
    val body: String,
    val date: String,
    val time: String,
    val avatarColor: Color,
)
