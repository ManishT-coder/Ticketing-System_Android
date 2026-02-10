package com.manish.myapplication.common.database.service

import android.content.Context
import com.manish.myapplication.common.database.AppDatabase
import com.manish.myapplication.common.database.entity.Users
import com.manish.myapplication.feature.screen.configuration.model.ConfigResponse

object UsersService{

    private val dao by lazy {
        AppDatabase.instance.usersDao()
    }

    suspend fun getUsers(): List<Users>{
        return dao.getAll()
    }


    suspend fun saveOrUpdate(users: List<Users>){
        return dao.insertAll(users)
    }

    suspend fun getUsersById(userID: Int): Users?{
        return dao.getById(userID)
    }
}