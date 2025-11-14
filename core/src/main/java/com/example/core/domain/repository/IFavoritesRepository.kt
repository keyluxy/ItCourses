package com.example.core.domain.repository

import com.example.core.domain.model.Course
import kotlinx.coroutines.flow.Flow

interface IFavoritesRepository {
    suspend fun addToFavorites(course: Course)
    suspend fun removeFromFavorites(courseId: Int)
    suspend fun isFavorite(courseId: Int): Boolean
    fun observeFavorites(): Flow<List<Course>>
    fun observeFavoriteIds(): Flow<Set<Int>>
}

