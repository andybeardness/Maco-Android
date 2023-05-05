package com.beardness.macosmsapp.source.repo.translate.dto

import com.beardness.macosmsapp.db.translate.TranslateDbEntity

data class TranslateRepoDto(
    val id: Int,
    val smsId: Int,
    val translatedAuto: String,
    val translatedGe: String,
)

fun TranslateDbEntity.repoDto(): TranslateRepoDto =
    TranslateRepoDto(
        id = id.toInt(),
        smsId = smsId.toInt(),
        translatedAuto = translatedAuto,
        translatedGe = translatedGe,
    )
