package com.beardness.macosmsapp.source.repo.translate.dto

import com.beardness.macosmsapp.db.translate.TranslateDbEntity

data class TranslateRepoDto(
    val id: Int,
    val smsId: Int,
    val languageCode: String,
    val translatedBody: String,
)

fun TranslateDbEntity.repoDto(): TranslateRepoDto =
    TranslateRepoDto(
        id = id.toInt(),
        smsId = smsId.toInt(),
        languageCode = langCode,
        translatedBody = translatedBody,
    )
