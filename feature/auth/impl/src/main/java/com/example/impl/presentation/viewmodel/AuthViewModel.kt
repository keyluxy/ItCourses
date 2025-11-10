package com.example.impl.presentation.viewmodel

import androidx.compose.ui.res.stringResource
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.api.model.AuthUser
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

    fun onEvent(event: AuthEvent) {
        when(event) {
            is AuthEvent.EmailChanged -> _state.update { it.copy(email = event.email) }
            is AuthEvent.PasswordChanged -> _state.update { it.copy(password = event.password) }
            is AuthEvent.OnLoginClicked -> login()
            is AuthEvent.OnRegsterClicked -> registration()
            is AuthEvent.ErrorDismissed -> _state.update { it.copy(errorResId = null) }
        }
    }

    private fun login() {
        val email = state.value.email
        val password = state.value.password
        viewModelScope.launch {
            _state.update { it.copy(isLoading = true, errorResId = null) }
            val result = loginUseCase(email, password)
            result.fold(
                onSuccess = {
                    _state.update { it.copy(isLoading = false) }
                },
                onFailure = {
                    _state.update { it.copy(isLoading = false, errorResId = R.string.login_error) }
                }
            )
        }
    }

    private fun registration() {
        val email = state.value.email
        val password = state.value.password
        viewModelScope.launch {
            _state.update { it.copy(isLoading = true, errorResId = null) }
            val result = registerUseCase(email = email, password = password)
            result.fold(
                onSuccess = {
                    _state.update { it.copy(isLoading = false) }
                },
                onFailure = {
                    _state.update { it.copy(isLoading = false, errorResId = R.string.register_error) }
                }
            )
        }
    }

}