package com.example.feature.favorites.impl.presentation

import com.example.core.domain.model.Course

data class FavoritesUiState(
    val title: String = "Избранное",
    val courses: List<Course> = emptyList(),
    val isLoading: Boolean = false
)





