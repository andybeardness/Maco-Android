package com.beardness.macosmsapp.db.translate

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.beardness.macosmsapp.source.repo.translate.dto.TranslateRepoDto

@Entity(tableName = "translate")
data class TranslateDbEntity(

    @ColumnInfo(name = "id")
    @PrimaryKey(autoGenerate = true)
    val id: Long,

    @ColumnInfo(name = "sms_id")
    val smsId: Long,

    @ColumnInfo(name = "lang_code")
    val langCode: String,

    @ColumnInfo(name = "translated_body")
    val translatedBody: String,
)

fun TranslateRepoDto.dbEntity(): TranslateDbEntity =
    TranslateDbEntity(
        id = this.id.toLong(),
        smsId = this.smsId.toLong(),
        langCode = this.languageCode,
        translatedBody = this.translatedBody,
    )
