package com.beardness.macosmsapp.screen.smsbyauthor.dto

data class SmsViewDto(
    val id: Int,
    val author: String,
    val body: String,
    val date: String,
    val time: String,
    val translates: SmsTranslateViewDto,
)
