package com.manish.myapplication.feature.screen.configuration

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import cafe.adriel.voyager.core.model.ScreenModel
import cafe.adriel.voyager.core.model.screenModelScope
import com.manish.myapplication.common.database.entity.Config
import com.manish.myapplication.common.database.service.ConfigService
import com.manish.myapplication.common.database.service.UsersService
import com.manish.myapplication.feature.screen.configuration.model.ConfigRequest
import com.manish.myapplication.feature.screen.configuration.model.ConfigResponse
import com.manish.myapplication.repository.ConfigRepo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.tinylog.Logger

class ConfigurationViewModel(
    private val navigateToLoginScreen: () -> Unit
) : ScreenModel {


    companion object {
        var instance: ConfigurationViewModel? = null
            private set
    }

    fun onCreate() {
        screenModelScope.launch(Dispatchers.IO) {

            instance = this@ConfigurationViewModel

            while (true) {

                if (fetchConfig()) {
                    withContext(Dispatchers.Main) {
                        navigateToLoginScreen()
                    }
                } else {
                    Logger.warn { "Config fetch failed, retrying in 2s..." }
                    delay(2000)
                }
            }
        }
    }
    suspend fun fetchConfig(): Boolean = withContext(Dispatchers.IO) {
        try {
            val request = ConfigRequest(
                ip = "10.1.5.113",
                eqtypeId = 2
            )

            Logger.info { "Request = $request" }

            val config = ConfigRepo.getConfig(request)

            if (config.status && config.data != null) {

                ConfigService.saveOrUpdate(config)

                config.data.users?.let {
                    UsersService.saveOrUpdate(it)
                }

                true
            } else {
                Logger.warn { "Invalid config response: $config" }
                false
            }

        } catch (e: Exception) {
            Logger.error(e)
            false
        }
    }




}