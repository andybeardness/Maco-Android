package com.beardness.macosmsapp.db.smscache

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface SmsCacheDao {

    @Query("SELECT * FROM smscache")
    fun flow(): Flow<List<SmsCacheDbEntity>>

    @Query("SELECT * FROM smscache")
    suspend fun all(): List<SmsCacheDbEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addAll(elements: List<SmsCacheDbEntity>)

    @Query("DELETE FROM smscache")
    suspend fun clearAll()
}