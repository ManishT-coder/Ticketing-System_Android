package com.manish.myapplication.repository

import com.manish.myapplication.common.database.dao.ConfigDao
import com.manish.myapplication.common.database.entity.Config
import com.manish.myapplication.common.network.ApiManager
import com.manish.myapplication.feature.screen.configuration.model.ConfigRequest

object ConfigRepo{

    suspend fun getConfig(request: ConfigRequest): Config {

        // 2. API Call
        val response = ApiManager.getInstance().getConfig(request)
        val body = response.body()

        if (response.isSuccessful && body != null) {
            val apiConfig = body.data?.config

            if (apiConfig != null) {
                return apiConfig
            } else {
                throw Exception("API 'config' object is missing")
            }
        } else {
            throw Exception("Server Error: ${response.message()}")
        }
    }
}
