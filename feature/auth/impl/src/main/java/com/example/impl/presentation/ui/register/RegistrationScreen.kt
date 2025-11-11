package com.example.impl.presentation.ui.register

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.navigation.NavController
import com.example.api.navigation.AuthRoutes
import com.example.impl.presentation.event.AuthEvent
import com.example.impl.presentation.viewmodel.AuthViewModel
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel

@Composable
fun RegistrationScreen(
    navController: NavController,
    viewModel: AuthViewModel = hiltViewModel(),
) {
    val state by viewModel.state.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.onAuthSuccess = {
            navController.navigate(AuthRoutes.MAIN) {
                popUpTo(AuthRoutes.REGISTER) { inclusive = true }
            }
        }
    }

    RegistrationScreenContent(
        state = state,
        onEvent = viewModel::onEvent,
        onRegisterClick = { viewModel.onEvent(AuthEvent.OnRegisterClicked) },
        onLoginClick = { navController.navigate(AuthRoutes.LOGIN) }
    )
}

