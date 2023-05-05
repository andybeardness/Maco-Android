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

    @ColumnInfo(name = "translated_auto")
    val translatedAuto: String,

    @ColumnInfo(name = "translated_ge")
    val translatedGe: String,
)

fun TranslateRepoDto.dbEntity(): TranslateDbEntity =
    TranslateDbEntity(
        id = this.id.toLong(),
        smsId = this.smsId.toLong(),
        translatedAuto = this.translatedAuto,
        translatedGe = this.translatedGe,
    )
