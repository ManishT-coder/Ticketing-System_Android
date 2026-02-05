package com.manish.myapplication.common.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.manish.myapplication.common.database.entity.Passes


@Dao
interface PassesDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(passes: List<Passes>)

    @Query("SELECT * FROM Passes")
    suspend fun getAll(): List<Passes>

    @Query("SELECT * FROM Passes WHERE id=:PassId")
    suspend fun getById(PassId: Int): Passes?

    @Query("DELETE FROM Passes")
    suspend fun deleteAll()

    @Query("DELETE FROM Passes WHERE id=:Passid")
    suspend fun deleteById(Passid: Int)
}