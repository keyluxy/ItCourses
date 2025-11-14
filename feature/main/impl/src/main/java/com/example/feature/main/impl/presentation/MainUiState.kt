package com.example.feature.main.impl.presentation

import com.example.core.domain.model.Course

data class MainUiState(
    val title: String = "Главная",
    val courses: List<Course> = emptyList(),
    val isLoading: Boolean = false
)





