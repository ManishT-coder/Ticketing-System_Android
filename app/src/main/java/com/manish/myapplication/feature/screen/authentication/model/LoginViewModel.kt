package com.manish.myapplication.feature.screen.authentication.model

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import cafe.adriel.voyager.core.model.ScreenModel
import cafe.adriel.voyager.core.model.screenModelScope
import com.manish.myapplication.common.database.entity.Config
import com.manish.myapplication.feature.screen.configuration.model.ConfigRequest
import com.manish.myapplication.repository.ConfigRepo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class LoginViewModel(
    private val configRepo: ConfigRepo
) : ScreenModel {

    var username by mutableStateOf("")
    var password by mutableStateOf("")
    var showError by mutableStateOf(false)

    var config by mutableStateOf<Config?>(null)
        private set

    val stationName: String
        get() = config?.StnName ?: ""

    var uiState by mutableStateOf<LoginUiState>(LoginUiState.Loading)
        private set

    fun loadConfig() {
        screenModelScope.launch(Dispatchers.IO) {
            try {
                val request = ConfigRequest(
                    ip = "10.14.3.108",
                    eqtypeId = 2
                )
                org.tinylog.Logger.info{"Request = { $request }"}

                config = configRepo.getConfig(request)


            } catch (e: Exception) {
                uiState = LoginUiState.Error(
                    e.message ?: "Unable to load configuration"
                )
            }
        }
    }

    fun validateLogin() {
        if (username == "1802" && password == "1802") {
            // navigate next
        } else {
            showError = true
        }
    }
}