package com.example.impl.presentation.event

sealed interface AuthEvent {
    data class EmailChanged(val email: String,) : AuthEvent
    data class PasswordChanged(val password: String) : AuthEvent
    object OnLoginClicked : AuthEvent
    object OnRegsterClicked : AuthEvent
    object ErrorDismissed : AuthEvent
}