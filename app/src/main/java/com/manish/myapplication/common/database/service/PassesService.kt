package com.manish.myapplication.common.database.service

import android.content.Context
import com.manish.myapplication.common.database.AppDatabase
import com.manish.myapplication.common.database.entity.Passes


class PassesService(){

    private val dao by lazy {
        AppDatabase.instance.passesDao()
    }
    suspend fun getPasses(): List<Passes>{
        return dao.getAll()
    }

    suspend fun getPassesById(passesID: Int): Passes?{
        return dao.getById(passesID)
    }

}