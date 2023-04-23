package com.beardness.macosmsapp.screen.smsbygroup.dto

data class SmsViewDto(
    val id: Int,
    val address: String,
    val body: String,
    val date: String,
    val translates: List<TranslateViewDto>
)
