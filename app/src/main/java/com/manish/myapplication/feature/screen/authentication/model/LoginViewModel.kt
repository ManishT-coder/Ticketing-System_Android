package com.manish.myapplication.feature.screen.authentication.model

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import cafe.adriel.voyager.core.model.ScreenModel
import cafe.adriel.voyager.core.model.screenModelScope
import com.manish.myapplication.common.database.entity.Config
import com.manish.myapplication.common.database.entity.Stations
import com.manish.myapplication.common.database.entity.Users
import com.manish.myapplication.common.database.service.ConfigService
import com.manish.myapplication.common.database.service.UsersService
import com.manish.myapplication.feature.screen.configuration.model.ConfigRequest
import com.manish.myapplication.feature.screen.configuration.model.ConfigResponse
import com.manish.myapplication.repository.ConfigRepo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.tinylog.Logger

class LoginViewModel(
    private val configRepo: ConfigRepo
) : ScreenModel {

    var username by mutableStateOf("")
    var userError by mutableStateOf<String?>(null)

    var isLoading by mutableStateOf(false)

    var password by mutableStateOf("")
    var passwordError by mutableStateOf<String?>(null)

    var config by mutableStateOf<ConfigResponse?>(null)

    var loginSuccess by mutableStateOf(false)   // 👈 ADD THIS

    fun onCreate() {
        screenModelScope.launch(Dispatchers.IO) {
            config = ConfigService.getConfig()
            Logger.info { "Config on LoginViewModel= $config" }
        }
    }

    fun validateLogin() {
        screenModelScope.launch {

            // 1️⃣ Validation (Main Thread)
            if (username.isBlank()) {
                userError = "Username required"
                return@launch
            }

            if (password.isBlank()) {
                passwordError = "Password required"
                return@launch
            }

            // 2️⃣ Database call on IO thread
            val users = withContext(Dispatchers.IO) {
                UsersService.getUsers()
            }

            // 3️⃣ Same logic as yours
            val user = users.find { it.UserLogin == username }

            if (user == null) {
                userError = "User Not Found!"
                passwordError = null
            } else {
                userError = null

                if (user.UserPawd == password) {
                    passwordError = null
                    loginSuccess = true   // ✅ navigation will now work
                } else {
                    passwordError = "Wrong Password!"
                }
            }
        }
    }

}
