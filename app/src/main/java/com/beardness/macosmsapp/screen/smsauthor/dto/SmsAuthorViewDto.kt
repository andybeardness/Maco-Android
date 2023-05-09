package com.beardness.macosmsapp.screen.smsauthor.dto

data class SmsAuthorViewDto(
    val id: Int,
    val author: String,
    val body: String,
    val date: String,
    val time: String,
    val translates: SmsTranslateViewDto,
)
