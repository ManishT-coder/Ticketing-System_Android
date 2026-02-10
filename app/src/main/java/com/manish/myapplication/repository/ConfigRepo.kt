package com.manish.myapplication.repository

import com.manish.myapplication.common.database.entity.Config
import com.manish.myapplication.common.network.ApiManager
import com.manish.myapplication.feature.screen.configuration.model.ConfigRequest
import com.manish.myapplication.feature.screen.configuration.model.ConfigResponse
import java.util.logging.Logger

object ConfigRepo{

    suspend fun getConfig(request: ConfigRequest): ConfigResponse {

        // 2. API Call
        val response = ApiManager.getInstance().getConfig(request)

        val config = response.body()

        org.tinylog.Logger.info { "Config = ${config}" }

        if(config == null){
            throw Exception("Config data is null !")
        }
        return config
    }
}
