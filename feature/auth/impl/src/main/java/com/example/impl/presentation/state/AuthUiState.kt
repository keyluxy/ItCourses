package com.example.impl.presentation.state

data class AuthUiState(
    val email: String = "",
    val password: String = "",
    val confirmPassword: String = "",
    val errorMessage: String? = null,
    val isLoading: Boolean = false,
)
