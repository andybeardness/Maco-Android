package com.beardness.macosmsapp.source.repo.smscache.dto

import com.beardness.macosmsapp.db.smscache.SmsCacheDbEntity
import com.beardness.macosmsapp.source.repo.sms.dto.SmsRepoDto

data class SmsCacheProxyRepoDto(
    val id: Long,
    val smsId: Int,
    val author: String,
    val body: String,
    val date: Long,
)

fun SmsCacheDbEntity.proxyRepoDto(): SmsCacheProxyRepoDto =
    SmsCacheProxyRepoDto(
        id = this.id,
        smsId = this.smsId.toInt(),
        author = this.author,
        body = this.body,
        date = this.date,
    )

fun SmsRepoDto.proxyRepoDto(): SmsCacheProxyRepoDto =
    SmsCacheProxyRepoDto(
        id = 0,
        smsId = this.id,
        author = this.address,
        body = this.body,
        date = this.date,
    )
