package com.manish.myapplication.feature.screen.authentication.model

sealed class LoginUiState {
    object Loading : LoginUiState()
    object Ready : LoginUiState()        // Config fetched → show login
    data class Error(val message: String) : LoginUiState()
}