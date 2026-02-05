package com.manish.myapplication.common.database.service

import android.content.Context
import com.manish.myapplication.common.database.AppDatabase
import com.manish.myapplication.common.database.entity.Config

class ConfigService(context: Context) {

    private val dao = AppDatabase.Companion.getInstance(context).configDao()

    suspend fun getConfig(): List<Config>{
        return dao.getAll()
    }

    suspend fun getByIdConfig(configId: Int): Config?{
        return dao.getByID(configId)
    }

}