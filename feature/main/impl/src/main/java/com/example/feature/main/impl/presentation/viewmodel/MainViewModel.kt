package com.example.feature.main.impl.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import com.example.core.database.dao.FavoriteCourseDao
import com.example.core.database.mapper.toFavoriteEntity
import com.example.core.network.domain.ICoursesRepository
import com.example.feature.main.impl.presentation.MainUiState

@HiltViewModel
class MainViewModel @Inject constructor(
    private val coursesRepository: ICoursesRepository,
    private val favoriteCourseDao: FavoriteCourseDao
) : ViewModel() {

    private val _uiState: MutableStateFlow<MainUiState> = MutableStateFlow(MainUiState())
    val uiState: StateFlow<MainUiState> = _uiState

    init {
        loadCourses()
        observeFavorites()
    }

    private fun loadCourses() {
        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true) }
            coursesRepository.getCourses().fold(
                onSuccess = { courses ->
                    // Загружаем избранные курсы и обновляем hasLike
                    val favoritesList = favoriteCourseDao.getAllFavorites().first()
                    val favoriteIdsSet = favoritesList.map { it.courseId }.toSet()
                    val updatedCourses = courses.map { course ->
                        course.copy(hasLike = course.id in favoriteIdsSet)
                    }
                    _uiState.update { it.copy(courses = updatedCourses, isLoading = false) }
                },
                onFailure = { error ->
                    _uiState.update { it.copy(isLoading = false) }
                    // TODO: Обработка ошибки
                }
            )
        }
    }

    private fun observeFavorites() {
        viewModelScope.launch {
            favoriteCourseDao.getAllFavorites()
                .map { favorites -> favorites.map { it.courseId }.toSet() }
                .collect { favoriteIdsSet ->
                    // Обновляем состояние избранного для всех курсов только если курсы уже загружены
                    _uiState.update { currentState ->
                        if (currentState.courses.isNotEmpty()) {
                            val updatedCourses = currentState.courses.map { course ->
                                course.copy(hasLike = course.id in favoriteIdsSet)
                            }
                            currentState.copy(courses = updatedCourses)
                        } else {
                            currentState
                        }
                    }
                }
        }
    }

    fun sortCoursesByPublishDate() {
        _uiState.update { currentState ->
            val sortedCourses = currentState.courses.sortedByDescending { it.publishDate }
            currentState.copy(courses = sortedCourses)
        }
    }

    fun toggleFavorite(course: com.example.core.network.data.Course) {
        viewModelScope.launch {
            val existingFavorite = favoriteCourseDao.getFavoriteByCourseId(course.id)
            if (existingFavorite != null) {
                favoriteCourseDao.deleteByCourseId(course.id)
            } else {
                favoriteCourseDao.insertFavorite(course.toFavoriteEntity())
            }
            // Обновляем состояние курсов локально
            _uiState.update { currentState ->
                val updatedCourses = currentState.courses.map { c ->
                    if (c.id == course.id) {
                        c.copy(hasLike = !c.hasLike)
                    } else {
                        c
                    }
                }
                currentState.copy(courses = updatedCourses)
            }
        }
    }
}





