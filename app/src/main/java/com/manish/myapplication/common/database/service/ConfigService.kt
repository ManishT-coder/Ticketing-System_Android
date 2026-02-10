package com.manish.myapplication.common.database.service

import com.manish.myapplication.common.database.AppDatabase
import com.manish.myapplication.common.database.entity.Config
import com.manish.myapplication.feature.screen.configuration.model.ConfigResponse

object ConfigService {

    private val dao by lazy {
        AppDatabase.instance.configDao()
    }

    suspend fun saveOrUpdate(config: ConfigResponse): Long {
        return dao.insertConfig(config)
    }
    suspend fun getConfig(): ConfigResponse? {
        return dao.getAll()
    }
//
//    suspend fun getByIdConfig(configId: Int): Config?{
//        return dao.getByID(configId)
//    }

}