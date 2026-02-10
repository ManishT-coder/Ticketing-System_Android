package com.manish.myapplication.common.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.manish.myapplication.common.database.entity.Config
import com.manish.myapplication.feature.screen.configuration.model.ConfigResponse


@Dao
interface ConfigDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertConfig(config: ConfigResponse): Long

    @Query("SELECT * FROM config_response")
    suspend fun getAll(): ConfigResponse?
    
    @Query("DELETE FROM config_response WHERE id=:ConfigId")
    suspend fun deleteById(ConfigId: Int)

}