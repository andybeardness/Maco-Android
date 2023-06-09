package com.beardness.macosmsapp.screen.smsauthor.dto

data class SmsTranslateViewDto(
    val auto: String? = null,
    val georgian: String? = null,
)

val SmsTranslateViewDto.exist: Boolean
    get() = this.auto != null
            || this.georgian != null
