package com.manish.myapplication.common.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.manish.myapplication.common.database.entity.CardsConfig


@Dao
interface CardtypeDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(items: List<CardsConfig>)

    @Query("SELECT * FROM CARD_CONFIG")
    suspend fun getAll(): List<CardsConfig>


    @Query("SELECT * FROM CARD_CONFIG  WHERE CardTypeId = :cardTypeId")
    suspend fun getById(cardTypeId: Int): List<CardsConfig>

    @Query("DELETE FROM CARD_CONFIG")
    suspend fun deleteAll()


}