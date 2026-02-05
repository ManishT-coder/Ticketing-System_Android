package com.manish.myapplication.feature.screen.configuration

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import cafe.adriel.voyager.core.model.rememberScreenModel
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.Navigator
import cafe.adriel.voyager.navigator.currentOrThrow
import com.manish.myapplication.feature.screen.authentication.LoginScreen

object ConfigurationScreen: Screen {
    private fun readResolve(): Any = ConfigurationScreen

    private var navigator: Navigator? = null

    @Composable
    override fun Content() {
        navigator = LocalNavigator.currentOrThrow
        val viewModel = rememberScreenModel { ConfigurationViewModel(
            navigateToLoginScreen = { navigator?.replace(LoginScreen)}
        ) }

        LaunchedEffect(Unit) { 
            viewModel.onCreate()
        }
        View()
    }

    @Composable
    fun View() {
        Column(
            Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Box(
                Modifier.fillMaxSize(.5f),
            ) {
                Column(
                    Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    CircularProgressIndicator(
                        Modifier.padding(32.dp),
                        color = MaterialTheme.colorScheme.primary,
                        strokeWidth = 6.dp
                    )
                    Text("Please Wait for Configuration...")
                }
            }
        }
    }

}