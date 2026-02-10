package com.manish.myapplication.feature.screen.configuration

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import cafe.adriel.voyager.core.model.ScreenModel
import cafe.adriel.voyager.core.model.screenModelScope
import com.manish.myapplication.common.database.service.ConfigService
import com.manish.myapplication.common.database.service.UsersService
import com.manish.myapplication.feature.screen.configuration.model.ConfigRequest
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
        screenModelScope.launch {

            instance = this@ConfigurationViewModel

            // 1️⃣ First check if config already exists locally
            val localConfig = withContext(Dispatchers.IO) {
                ConfigService.getConfig()   // must return stored config or null
            }

            if (localConfig != null) {
                Logger.info { "Config found locally. Navigating to Login." }
                navigateToLoginScreen()
            } else {
                Logger.info { "No local config found. Fetching from server..." }
                fetchUntilSuccess()
            }
        }
    }

    // 🔁 Fetch until success (only first time)
    private suspend fun fetchUntilSuccess() {

        while (true) {

            val success = fetchConfig()

            if (success) {
                Logger.info { "Config fetched successfully. Navigating to Login." }
                navigateToLoginScreen()
                break   // stop loop after success
            } else {
                Logger.warn { "Fetch failed. Retrying in 2 seconds..." }
                delay(2000)
            }
        }
    }

    // 🌐 Fetch config from API
    suspend fun fetchConfig(): Boolean = withContext(Dispatchers.IO) {
        try {

            val request = ConfigRequest(
                ip = "10.1.5.113",
                eqtypeId = 2
            )

            Logger.info { "Request = $request" }

            val response = ConfigRepo.getConfig(request)

            if (response.status && response.data != null) {

                // Save config locally
                ConfigService.saveOrUpdate(response)

                // Save users locally
                response.data.users.let {
                    UsersService.saveOrUpdate(it)
                }

                true
            } else {
                Logger.warn { "Invalid response: $response" }
                false
            }

        } catch (e: Exception) {
            Logger.error(e)
            false
        }
    }
}
