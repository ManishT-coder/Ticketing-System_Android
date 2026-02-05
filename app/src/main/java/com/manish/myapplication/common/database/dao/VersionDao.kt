package com.manish.myapplication.common.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.manish.myapplication.common.database.entity.Version


@Dao
interface VersionDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(versions: List<Version>)

    @Query("SELECT * FROM Version")
    suspend fun getAll(): List<Version>

    @Query("SELECT * FROM Version WHERE verId=:VerId")
    suspend fun getById(VerId: Int): Version?

    @Query("DELETE FROM version")
    suspend fun deleteAll()

    @Query("DELETE FROM version WHERE verId=:VerID")
    suspend fun deleteById(VerID: Int)
}