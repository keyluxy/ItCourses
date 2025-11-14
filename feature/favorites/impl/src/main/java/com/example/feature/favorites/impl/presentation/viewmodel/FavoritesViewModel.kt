package com.example.feature.favorites.impl.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import com.example.core.database.dao.FavoriteCourseDao
import com.example.core.database.mapper.toCourse
import com.example.feature.favorites.impl.presentation.FavoritesUiState

@HiltViewModel
class FavoritesViewModel @Inject constructor(
    private val favoriteCourseDao: FavoriteCourseDao
) : ViewModel() {

    private val _uiState: MutableStateFlow<FavoritesUiState> = MutableStateFlow(FavoritesUiState())
    val uiState: StateFlow<FavoritesUiState> = _uiState

    init {
        loadFavorites()
    }

    private fun loadFavorites() {
        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true) }
            favoriteCourseDao.getAllFavorites()
                .map { favorites ->
                    favorites.map { it.toCourse() }
                }
                .collect { courses ->
                    _uiState.update { it.copy(courses = courses, isLoading = false) }
                }
        }
    }

    fun removeFavorite(courseId: Int) {
        viewModelScope.launch {
            favoriteCourseDao.deleteByCourseId(courseId)
            loadFavorites()
        }
    }
}





