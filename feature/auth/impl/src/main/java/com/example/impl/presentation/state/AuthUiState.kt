package com.example.impl.presentation.state

data class AuthUiState(
    val email: String = "",
    val password: String = "",
    val errorResId: Int? = null,
    val isLoading: Boolean = false,
)
