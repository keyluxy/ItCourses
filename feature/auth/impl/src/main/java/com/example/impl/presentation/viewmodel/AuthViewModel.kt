package com.example.impl.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.impl.R
import com.example.impl.domain.usecase.LoginUseCase
import com.example.impl.domain.usecase.RegisterUseCase
import com.example.impl.presentation.event.AuthEvent
import com.example.impl.presentation.state.AuthUiState
import com.example.impl.utils.validate
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
            is AuthEvent.EmailChanged -> _state.update { validate(it.copy(email = event.email, isFormValidated = false)) }
            is AuthEvent.PasswordChanged -> _state.update { validate(it.copy(password = event.password, isFormValidated = false)) }
            is AuthEvent.ConfirmPasswordChanged -> _state.update { validate(it.copy(confirmPassword = event.confirmPassword, isFormValidated = false), forRegistration = true) }
            is AuthEvent.OnLoginClicked -> {
                _state.update { validate(it.copy(isFormValidated = true)) }
                if (state.value.emailError == null && state.value.passwordError == null) login()
            }
            is AuthEvent.OnRegisterClicked -> {
                _state.update { validate(it.copy(isFormValidated = true), forRegistration = true) }
                if (state.value.emailError == null &&
                    state.value.passwordError == null &&
                    state.value.confirmPasswordError == null)
                    registration()
            }
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
                    onAuthSuccess?.invoke()
                },
                onFailure = {
                    _state.update { it.copy(isLoading = false, errorResId = R.string.user_not_found_error) }
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
                    onAuthSuccess?.invoke()
                },
                onFailure = {
                    _state.update { it.copy(isLoading = false, errorResId = R.string.register_error) }
                }
            )
        }
    }

}