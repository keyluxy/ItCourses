package com.example.impl.presentation.ui.login

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.navigation.NavController
import com.example.api.navigation.AuthRoutes
import com.example.impl.presentation.event.AuthEvent
import com.example.impl.presentation.viewmodel.AuthViewModel
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import com.example.impl.presentation.viewmodel.AuthNavigationEvent

// feature-auth-impl/src/main/java/com/example/impl/presentation/ui/login/LoginScreen.kt
@Composable
fun LoginScreen(
    onLoginSuccess: () -> Unit,
    onNavigateToRegister: () -> Unit,
    viewModel: AuthViewModel = hiltViewModel(),
) {
    val state by viewModel.state.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.navigationEvent.collect { event ->
            when (event) {
                is AuthNavigationEvent.NavigateToMain -> onLoginSuccess()
            }
        }
    }

    LoginScreenContent(
        state = state,
        onEvent = viewModel::onEvent,
        onLoginClick = { viewModel.onEvent(AuthEvent.OnLoginClicked) },
        onRegisterClick = onNavigateToRegister
    )
}