package com.example.core.data.repository

import com.example.core.data.local.FavoriteCourseDao
import com.example.core.data.mapper.toDomain
import com.example.core.data.mapper.toFavoriteEntity
import com.example.core.domain.model.Course
import com.example.core.domain.repository.IFavoritesRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class FavoritesRepositoryImpl @Inject constructor(
    private val favoriteCourseDao: FavoriteCourseDao
) : IFavoritesRepository {

    override suspend fun addToFavorites(course: Course) {
        favoriteCourseDao.insertFavorite(course.toFavoriteEntity())
    }

    override suspend fun removeFromFavorites(courseId: Int) {
        favoriteCourseDao.deleteByCourseId(courseId)
    }

    override suspend fun isFavorite(courseId: Int): Boolean {
        return favoriteCourseDao.getFavoriteByCourseId(courseId) != null
    }

    override fun observeFavorites(): Flow<List<Course>> {
        return favoriteCourseDao.getAllFavorites()
            .map { entities -> entities.toDomain() }
    }

    override fun observeFavoriteIds(): Flow<Set<Int>> {
        return favoriteCourseDao.getAllFavorites()
            .map { entities -> entities.map { it.courseId }.toSet() }
    }
}

