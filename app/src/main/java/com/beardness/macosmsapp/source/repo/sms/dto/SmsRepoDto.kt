package com.beardness.macosmsapp.source.repo.sms.dto

data class SmsRepoDto(
    val id: Int,
    val address: String,
    val body: String,
    val date: Long,
)
