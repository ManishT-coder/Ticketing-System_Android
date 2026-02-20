package com.manish.myapplication.feature.screen.authentication

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.*
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cafe.adriel.voyager.core.screen.Screen
import com.manish.myapplication.feature.screen.authentication.model.LoginViewModel
import com.manish.myapplication.repository.ConfigRepo

object LoginScreen : Screen {
    private fun readResolve(): Any = LoginScreen

    @Composable
    override fun Content() {

        val navigator = cafe.adriel.voyager.navigator.LocalNavigator.current

        val viewModel = remember {
            LoginViewModel(configRepo = ConfigRepo)
        }

        LaunchedEffect(Unit) {
            viewModel.onCreate()
        }

        LaunchedEffect(viewModel.loginSuccess) {
            if (viewModel.loginSuccess) {
                viewModel.loginSuccess = false   // reset state
                navigator?.replace(WelcomeScreen)
            }
        }
        LoginView(viewModel)
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
                            text = viewModel.config?.data?.config?.StnName ?: "",
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

                Box(
                    modifier = Modifier
                        .weight(1f)
                        .fillMaxHeight()
                )

                Column(
                    modifier = Modifier
                        .weight(1f)
                        .fillMaxHeight(),
                    color = Color.Transparent
                ) {
                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        Surface(
                            modifier = Modifier
                                .fillMaxWidth()
                                .widthIn(max = 420.dp),
                            shape = RoundedCornerShape(16.dp),
                            tonalElevation = 0.dp,   // ✅ remove shadow
                            shadowElevation = 0.dp,  // ✅ remove shadow
                            color = Color.White,
                            border = BorderStroke(1.dp, Color(0xFFE2E8F0)) // clean border
                        ) {
                            Column(
                                modifier = Modifier.padding(24.dp),
                                verticalArrangement = Arrangement.spacedBy(14.dp),
                                horizontalAlignment = Alignment.Start
                            ) {

                                // ✅ Login heading in main area (not top bar)
                                Text(
                                    text = "Login",
                                    fontSize = 24.sp,
                                    fontWeight = FontWeight.Bold,
                                    color = Color(0xFF0F172A)
                                )

                    Spacer(modifier = Modifier.height(20.dp))

                                // USERNAME (no icon)
                                OutlinedTextField(
                                    value = viewModel.username,
                                    onValueChange = {
                                        viewModel.username = it
                                        viewModel.userError = null
                                    },
                                    label = { Text("Username") },
                                    singleLine = true,
                                    modifier = Modifier.fillMaxWidth(),
                                    isError = viewModel.userError != null,
                                    supportingText = {
                                        viewModel.userError?.let {
                                            Text(it, color = MaterialTheme.colorScheme.error)
                                        }
                                    },
                                    keyboardOptions = KeyboardOptions(
                                        imeAction = ImeAction.Next,
                                        keyboardType = KeyboardType.Number
                                    )
                                )

                                // PASSWORD (no icon)
                                OutlinedTextField(
                                    value = viewModel.password,
                                    onValueChange = {
                                        viewModel.password = it
                                        viewModel.passwordError = null
                                    },
                                    label = { Text("Password") },
                                    singleLine = true,
                                    visualTransformation = PasswordVisualTransformation(),
                                    modifier = Modifier.fillMaxWidth(),
                                    isError = viewModel.passwordError != null,
                                    supportingText = {
                                        viewModel.passwordError?.let {
                                            Text(it, color = MaterialTheme.colorScheme.error)
                                        }
                                    },
                                    keyboardOptions = KeyboardOptions(
                                        imeAction = ImeAction.Done,
                                        keyboardType = KeyboardType.NumberPassword
                                    )
                                )

                    Spacer(modifier = Modifier.height(20.dp))

                    // ✅ LOGIN BUTTON
                    Button(
                        onClick = { viewModel.validateLogin() },
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(48.dp),
                        shape = RoundedCornerShape(10.dp),
                        enabled = !viewModel.isLoading   // disable when loading
                    ) {

                        if (viewModel.isLoading) {
                            CircularProgressIndicator(
                                strokeWidth = 2.dp,
                                modifier = Modifier.size(20.dp)
                            )
                        } else {
                            Text(
                                text = "LOGIN",
                                fontWeight = FontWeight.Bold,
                                fontSize = 16.sp
                            )
                        }
                    }
                }
            }
        }
    }



}