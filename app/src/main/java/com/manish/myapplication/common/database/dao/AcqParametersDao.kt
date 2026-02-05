package com.manish.myapplication.common.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.manish.myapplication.common.database.entity.AcqParameters

@Dao
interface AcqParametersDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(parameters: List<AcqParameters>)

    @Query("SELECT * FROM AcqParameters")
    suspend fun getAll(): List<AcqParameters>

    @Query("SELECT * FROM AcqParameters WHERE AcqParaId=:AcqID")
    suspend fun getById(AcqID: Int): AcqParameters?

    @Query("DELETE FROM AcqParameters")
    suspend fun deleteAll()

    @Query("DELETE FROM AcqParameters WHERE AcqParaId=:AcqId")
    suspend fun deleteById(AcqId: Int)
}