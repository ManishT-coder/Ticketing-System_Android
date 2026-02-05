package com.manish.myapplication.common.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.manish.myapplication.common.database.entity.Stations

@Dao
interface StationsDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(station: Stations)

    @Query("SELECT * FROM stations")
    suspend fun getAll(): List<Stations>

    @Query("SELECT * FROM Stations WHERE StnId = :stnId")
    suspend fun getById(stnId: Int): Stations?

    @Query("DELETE FROM Stations")
    suspend fun deleteAll()

    @Query("DELETE FROM Stations WHERE Stid=:stnID ")
    suspend fun deleteById(stnID: Int)

}