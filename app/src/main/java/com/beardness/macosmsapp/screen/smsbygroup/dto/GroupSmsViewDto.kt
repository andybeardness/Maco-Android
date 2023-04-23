package com.beardness.macosmsapp.screen.smsbygroup.dto

import androidx.compose.ui.graphics.Color

data class GroupSmsViewDto(
    val author: String,
    val body: String,
    val date: String,
    val time: String,
    val avatarColor: Color,
)
