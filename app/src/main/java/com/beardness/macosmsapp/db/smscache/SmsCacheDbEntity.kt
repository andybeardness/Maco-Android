package com.beardness.macosmsapp.db.smscache

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.beardness.macosmsapp.source.repo.sms.dto.SmsRepoDto
import com.beardness.macosmsapp.source.repo.smscache.dto.SmsCacheProxyRepoDto

@Entity(tableName = "smscache")
data class SmsCacheDbEntity(

    @ColumnInfo(name = "id")
    @PrimaryKey(autoGenerate = true)
    val id: Long,

    @ColumnInfo(name = "sms_id")
    val smsId: Long,

    @ColumnInfo(name = "author")
    val author: String,

    @ColumnInfo(name = "body")
    val body: String,

    @ColumnInfo(name = "date")
    val date: Long,
)

fun SmsRepoDto.dbEntity(): SmsCacheDbEntity =
    SmsCacheDbEntity(
        id = 0,
        smsId = this.id.toLong(),
        author = this.address,
        body = this.body,
        date = this.date
    )