package com.manish.myapplication.common.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.manish.myapplication.common.database.entity.Fares


@Dao
interface FaresDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(fares: List<Fares>)

    @Query("SELECT * FROM Fares") // Added space after SELECT
    suspend fun getAll(): List<Fares>

    @Query("SELECT * FROM Fares WHERE Fareid=:FareId")
    suspend fun getById(FareId: Int): Fares?

    @Query("DELETE FROM Fares")
    suspend fun deleteAll()

    @Query("DELETE FROM Fares WHERE Fareid=:FareID")
    suspend fun deleteById(FareID: Int)
}