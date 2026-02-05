package com.manish.myapplication.feature.screen.authentication

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.*
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cafe.adriel.voyager.core.screen.Screen
import com.manish.myapplication.common.database.AppDatabase
import com.manish.myapplication.feature.screen.authentication.model.LoginUiState
import com.manish.myapplication.feature.screen.authentication.model.LoginViewModel
import com.manish.myapplication.repository.ConfigRepo

object LoginScreen : Screen {
    private fun readResolve(): Any = LoginScreen

    @Composable
    override fun Content() {

        val context = LocalContext.current

        // 1️⃣ Room database
        val database = remember {
            AppDatabase.getInstance(context)
        }

        // 2️⃣ Repository
        val repo = remember {
            ConfigRepo(database.configDao())
        }

        // 3️⃣ ViewModel
        val viewModel = remember {
            LoginViewModel(repo)
        }

        // Load config only once
        LaunchedEffect(Unit) {
            viewModel.loadConfig()
        }

        when (val state = viewModel.uiState) {
            LoginUiState.Loading -> ConfigLoadingScreen()
            LoginUiState.Ready -> LoginView(viewModel)
            is LoginUiState.Error -> ConfigErrorScreen(
                message = state.message,
                onRetry = { viewModel.loadConfig() }
            )
        }
    }


// ---------------- LOGIN VIEW ----------------

    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    fun LoginView(viewModel: LoginViewModel) {

        Scaffold(
            topBar = {
                TopAppBar(
                    title = {
                        Text(
                            text = viewModel.stationName.ifBlank { "Login" },
                            fontWeight = FontWeight.Bold
                        )
                    },
                    colors = TopAppBarDefaults.topAppBarColors(
                        containerColor = MaterialTheme.colorScheme.primary,
                        titleContentColor = Color.White
                    )
                )
            }
        ) { padding ->

            Row(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(padding)
            ) {

                // Left empty section (future branding)
                Box(
                    modifier = Modifier
                        .weight(1f)
                        .fillMaxHeight()
                )

                // Right login section
                Column(
                    modifier = Modifier
                        .weight(1f)
                        .fillMaxHeight()
                        .padding(24.dp),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {

                    Text(
                        text = "Login",
                        fontSize = 22.sp,
                        fontWeight = FontWeight.Bold
                    )

                    Spacer(modifier = Modifier.height(20.dp))

                    OutlinedTextField(
                        value = viewModel.username,
                        onValueChange = {
                            viewModel.username = it
                            viewModel.showError = false
                        },
                        label = { Text("Username") },
                        singleLine = true,
                        modifier = Modifier.fillMaxWidth(),
                        keyboardOptions = KeyboardOptions(
                            imeAction = ImeAction.Next,
                            keyboardType = KeyboardType.Number
                        )
                    )

                    Spacer(modifier = Modifier.height(12.dp))

                    OutlinedTextField(
                        value = viewModel.password,
                        onValueChange = {
                            viewModel.password = it
                            viewModel.showError = false
                        },
                        label = { Text("Password") },
                        singleLine = true,
                        visualTransformation = PasswordVisualTransformation(),
                        modifier = Modifier.fillMaxWidth(),
                        keyboardOptions = KeyboardOptions(
                            imeAction = ImeAction.Done,
                            keyboardType = KeyboardType.NumberPassword
                        )
                    )

                    Spacer(modifier = Modifier.height(20.dp))

                    Button(
                        onClick = { viewModel.validateLogin() },
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(48.dp),
                        shape = RoundedCornerShape(10.dp)
                    ) {
                        Text(
                            text = "LOGIN",
                            fontWeight = FontWeight.Bold,
                            fontSize = 16.sp
                        )
                    }

                    if (viewModel.showError) {
                        Spacer(modifier = Modifier.height(12.dp))
                        Text(
                            text = "Invalid username or password",
                            color = Color.Red,
                            fontSize = 13.sp
                        )
                    }
                }
            }
        }
    }

    @Composable
    fun ConfigLoadingScreen() {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                CircularProgressIndicator(
                    strokeWidth = 3.dp
                )

                Spacer(modifier = Modifier.height(16.dp))

                Text(
                    text = "Loading station configuration...",
                    fontSize = 14.sp,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
        }
    }

    @Composable
    fun ConfigErrorScreen(
        message: String,
        onRetry: () -> Unit
    ) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.padding(24.dp)
            ) {

                Spacer(modifier = Modifier.height(12.dp))

                Text(
                    text = "Something went wrong",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.onSurface
                )

                Spacer(modifier = Modifier.height(6.dp))

                Text(
                    text = message,
                    fontSize = 14.sp,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )

                Spacer(modifier = Modifier.height(20.dp))

                Button(
                    onClick = onRetry,
                    shape = RoundedCornerShape(10.dp)
                ) {
                    Text("Retry")
                }
            }
        }
    }
}