package com.manish.myapplication.feature.screen.configuration

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
import org.tinylog.Logger

class ConfigurationViewModel(
    private val navigateToLoginScreen: () -> Unit
): ScreenModel {



    var config by mutableStateOf<Config?>(null)
        private set

     fun onCreate(){
        screenModelScope.launch(Dispatchers.IO) {
            try {
                val request = ConfigRequest(
                    ip = "10.14.3.108",
                    eqtypeId = 2
                )
                Logger.info{"Request = { $request }"}

                config = ConfigRepo.getConfig(request)

                if (config != null){
                    screenModelScope.launch(Dispatchers.Main) {
                        navigateToLoginScreen
                    }
                }

            } catch (e: Exception) {
                Exception(e)
            }
        }
    }


}