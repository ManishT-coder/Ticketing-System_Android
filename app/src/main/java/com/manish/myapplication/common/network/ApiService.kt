package com.manish.myapplication.common.network

import com.manish.myapplication.feature.screen.configuration.model.ConfigRequest
import com.manish.myapplication.feature.screen.configuration.model.ConfigResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface ApiService {

    @POST("v2/config")
    suspend fun getConfig(
        @Body request: ConfigRequest
    ): Response<ConfigResponse>

}