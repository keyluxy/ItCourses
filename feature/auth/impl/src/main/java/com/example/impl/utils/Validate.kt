package com.example.impl.utils

import com.example.impl.presentation.state.AuthUiState

fun validate(
    state: AuthUiState,
    forRegistration: Boolean = false
): AuthUiState {
    val emailPattern = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$"
    val hasCyrillic = state.email.any { it in 'а'..'я' || it in 'А'..'Я' }

    val emailError = when {
        state.email.isBlank() -> "Поле обязательно"
        hasCyrillic -> "Кириллица недоступна"
        !state.email.matches(emailPattern.toRegex()) -> "Неверный формат email"
        else -> null
    }
    val passwordError = when {
        state.password.isBlank() -> "Поле обязательно"
        state.password.length < 6 -> "Минимум 6 символов"
        else -> null
    }
    val confirmPasswordError = when {
        !forRegistration -> null
        state.confirmPassword.isBlank() -> "Поле обязательно"
        state.confirmPassword != state.password -> "Пароли не совпадают"
        else -> null
    }
    return state.copy(
        emailError = emailError,
        passwordError = passwordError,
        confirmPasswordError = confirmPasswordError
    )
}