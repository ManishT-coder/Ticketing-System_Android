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

        val stationName = viewModel.config?.data?.config?.StnName ?: "NA"

        Scaffold(
            topBar = {
                TopAppBar(
                    title = {
                        Text(
                            text = stationName.ifBlank { "NA" },
                            fontWeight = FontWeight.SemiBold,
                            fontSize = 22.sp,
                            maxLines = 1
                        )
                    },
                    colors = TopAppBarDefaults.topAppBarColors(
                        containerColor = Color(0xFF0F172A), // dark navy (not purple)
                        titleContentColor = Color.White
                    )
                )
            },
            containerColor = Color(0xFFF8FAFC) // very light background
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

                                // LOGIN BUTTON (good color, not purple)
                                Button(
                                    onClick = { viewModel.validateLogin() },
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .height(50.dp),
                                    shape = RoundedCornerShape(12.dp),
                                    enabled = !viewModel.isLoading,
                                    colors = ButtonDefaults.buttonColors(
                                        containerColor = Color(0xFF2563EB), // blue
                                        contentColor = Color.White,
                                        disabledContainerColor = Color(0xFF93C5FD),
                                        disabledContentColor = Color.White
                                    )
                                ) {
                                    if (viewModel.isLoading) {
                                        CircularProgressIndicator(
                                            strokeWidth = 2.dp,
                                            modifier = Modifier.size(20.dp),
                                            color = Color.White
                                        )
                                        Spacer(modifier = Modifier.width(10.dp))
                                        Text("Signing in...")
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
        }
    }

}
    @Preview(showBackground = true, device = "spec:parent=pixel_5,orientation=landscape")
    @Composable
    fun LoginScreenPreview() {
        MaterialTheme {
            LoginScreen.LoginView(
                viewModel = remember { LoginViewModel(configRepo = ConfigRepo) }
            )
        }
    }

}