package com.manish.myapplication.common.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.manish.myapplication.common.database.entity.Cardtype
import com.manish.myapplication.common.database.entity.Stations


@Dao
interface CardtypeDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(items: List<Cardtype>)

    @Query("SELECT * FROM Cardtype")
    suspend fun getAll(): List<Cardtype>

    @Query("SELECT * FROM Cardtype WHERE cardId=:CardID")
    suspend fun getById(CardID: Int): Cardtype?

    @Query("DELETE FROM Cardtype")
    suspend fun deleteAll()

    @Query("DELETE FROM Cardtype WHERE cardId=:CardId")
    suspend fun deleteById(CardId: Int)

}