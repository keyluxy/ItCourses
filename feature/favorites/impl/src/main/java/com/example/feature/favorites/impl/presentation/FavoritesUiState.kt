package com.example.feature.favorites.impl.presentation

import com.example.core.network.data.Course

data class FavoritesUiState(
    val title: String = "Избранное",
    val courses: List<Course> = emptyList(),
    val isLoading: Boolean = false
)





