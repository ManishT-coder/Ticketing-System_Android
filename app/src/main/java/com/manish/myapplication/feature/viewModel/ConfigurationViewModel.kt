package com.manish.myapplication.feature.viewModel

import androidx.compose.runtime.mutableStateOf
import cafe.adriel.voyager.core.model.ScreenModel
import cafe.adriel.voyager.core.model.screenModelScope
import com.manish.myapplication.common.database.entity.Config
import com.manish.myapplication.feature.screen.configuration.model.ConfigRequest
import com.manish.myapplication.repository.ConfigRepo
import kotlinx.coroutines.launch

class ConfigurationViewModel(
    private val repository: ConfigRepo  // inject repository
) : ScreenModel {

    val isLoading = mutableStateOf(true)
    val configData = mutableStateOf<Config?>(null)
    val error = mutableStateOf<String?>(null)

    init {
        fetchConfig()
    }

    private fun fetchConfig() {
        screenModelScope.launch {
            try {
                val config = repository.getConfig(
                    ConfigRequest(ip = "10.14.3.108", eqtypeId = 2)
                )
                configData.value = config
            } catch (e: Exception) {
                error.value = e.message
            } finally {
                isLoading.value = false
            }
        }

    }
}