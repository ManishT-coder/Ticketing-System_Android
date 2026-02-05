package com.manish.myapplication.common.database.service

import android.content.Context
import com.manish.myapplication.common.database.AppDatabase
import com.manish.myapplication.common.database.entity.Users

class UsersService(context: Context) {

    private val dao = AppDatabase.Companion.getInstance(context).usersDao()

    suspend fun getUsers(): List<Users>{
        return dao.getAll()
    }

    suspend fun getUsersById(userID: Int): Users?{
        return dao.getById(userID)
    }
}