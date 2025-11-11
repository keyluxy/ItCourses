package com.example.impl.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.impl.R
import com.example.impl.domain.usecase.LoginUseCase
import com.example.impl.domain.usecase.RegisterUseCase
import com.example.impl.presentation.event.AuthEvent
import com.example.impl.presentation.state.AuthUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(
    private val loginUseCase: LoginUseCase,
    private val registerUseCase: RegisterUseCase,
) : ViewModel() {

    private val _state = MutableStateFlow(AuthUiState())
    val state: StateFlow<AuthUiState> = _state.asStateFlow()

    var onAuthSuccess: (() -> Unit)? = null

    fun onEvent(event: AuthEvent) {
        when(event) {
            is AuthEvent.EmailChanged -> _state.update { it.copy(email = event.email) }
            is AuthEvent.PasswordChanged -> _state.update { it.copy(password = event.password) }
            is AuthEvent.ConfirmPasswordChanged -> _state.update { it.copy(confirmPassword = event.confirmPassword) }
            is AuthEvent.OnLoginClicked -> login()
            is AuthEvent.OnRegisterClicked -> registration()
            is AuthEvent.ErrorDismissed -> _state.update { it.copy(errorMessage = null) }
        }
    }

    private fun login() {
        val email = state.value.email
        val password = state.value.password
        if (email.isEmpty() || password.isEmpty()) {
            _state.update { it.copy(errorMessage = "Email и пароль обязательны") }
            return
        }
        viewModelScope.launch {
            _state.update { it.copy(isLoading = true, errorMessage = null) }
            val result = loginUseCase(email, password)
            result.fold(
                onSuccess = {
                    _state.update { it.copy(isLoading = false) }
                    onAuthSuccess?.invoke()
                },
                onFailure = { error ->
                    _state.update { it.copy(isLoading = false, errorMessage = error.message ?: "Ошибка Входа") }
                }
            )
        }
    }

    private fun registration() {
        val email = state.value.email
        val password = state.value.password
        val confirm = state.value.confirmPassword

        if (email.isEmpty() || password.isEmpty()) {
            _state.update { it.copy(errorMessage = "Email и пароль обязательны") }
            return
        }

        if (password != confirm) {
            _state.update { it.copy(errorMessage = "Пароли не совпадают") }
            return
        }

        viewModelScope.launch {
            _state.update { it.copy(isLoading = true, errorMessage = null) }
            val result = registerUseCase(email = email, password = password)
            result.fold(
                onSuccess = {
                    _state.update { it.copy(isLoading = false) }
                    onAuthSuccess?.invoke()
                },
                onFailure = { error ->
                    _state.update { it.copy(isLoading = false, errorMessage = error.message ?: "Ошибка регистрации") }
                }
            )
        }
    }

}