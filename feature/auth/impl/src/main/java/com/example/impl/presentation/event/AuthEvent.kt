package com.example.impl.presentation.event

sealed interface AuthEvent {
    data class EmailChanged(val email: String,) : AuthEvent
    data class PasswordChanged(val password: String) : AuthEvent
    data class ConfirmPasswordChanged(val confirmPassword: String) : AuthEvent
    object OnLoginClicked : AuthEvent
    object OnRegisterClicked : AuthEvent
    object ErrorDismissed : AuthEvent
}