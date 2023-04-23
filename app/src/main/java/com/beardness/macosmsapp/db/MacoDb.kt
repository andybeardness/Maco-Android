package com.beardness.macosmsapp.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.beardness.macosmsapp.db.smscache.SmsCacheDao
import com.beardness.macosmsapp.db.smscache.SmsCacheDbEntity
import com.beardness.macosmsapp.db.translate.TranslateDao
import com.beardness.macosmsapp.db.translate.TranslateDbEntity

@Database(
    entities = [
        TranslateDbEntity::class,
        SmsCacheDbEntity::class,
    ],
    version = 1,
)
abstract class MacoDb : RoomDatabase() {
    companion object {
        const val NAME = "MACO_DATABASE"
    }

    abstract fun translateDao(): TranslateDao
    abstract fun smsCacheDao(): SmsCacheDao
}