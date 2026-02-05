package com.manish.myapplication.common.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.manish.myapplication.common.database.entity.Users


@Dao
interface UsersDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(users: List<Users>)

    @Query("SELECT * FROM Users")
    suspend fun getAll(): List<Users>

    @Query("SELECT * FROM Users WHERE Id=:UserId")
    suspend fun getById(UserId: Int): Users?

    @Query("DELETE FROM Users")
    suspend fun deleteAll()

    @Query("DELETE FROM Users WHERE userId=:UserID")
    suspend fun deleteById(UserID: Int)
}