package com.manish.myapplication.common.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.manish.myapplication.common.database.entity.Config


@Dao
interface ConfigDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertConfig(config: Config)

    @Query("SELECT * FROM Config")
    suspend fun getAll(): List<Config>

    @Query("SELECT * FROM Config")
    suspend fun getConfig(): Config?

    @Query("SELECT * FROM Config WHERE LocalId =:ConfigID")
    suspend fun getByID(ConfigID: Int): Config?

    @Query("DELETE FROM Config")
    suspend fun deleteAll()

    @Query("DELETE FROM Config WHERE LocalId=:ConfigId")
    suspend fun deleteById(ConfigId: Int)

}