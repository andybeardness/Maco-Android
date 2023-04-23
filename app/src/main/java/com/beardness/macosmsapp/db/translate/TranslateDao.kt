package com.beardness.macosmsapp.db.translate

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface TranslateDao {

    @Query("SELECT * FROM translate")
    fun flow(): Flow<List<TranslateDbEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun create(entity: TranslateDbEntity)

    @Query("SELECT * FROM translate")
    suspend fun read(): List<TranslateDbEntity>

    @Query("SELECT * FROM translate WHERE sms_id = :smsId")
    suspend fun read(smsId: Int): List<TranslateDbEntity>

    @Query("SELECT * FROM translate WHERE id = :id")
    suspend fun read(id: Long): TranslateDbEntity?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun update(entity: TranslateDbEntity)

    @Delete
    suspend fun delete(entity: TranslateDbEntity)
}