package com.beardness.macosmsapp.usecase.flow.smstranslates.dto

data class SmsTranslateDto(
    val id: Int,
    val author: String,
    val body: String,
    val date: Long,
    val translates: TranslatesDto
)
