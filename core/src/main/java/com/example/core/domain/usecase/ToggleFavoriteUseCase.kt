package com.example.core.domain.usecase

import com.example.core.domain.model.Course
import com.example.core.domain.repository.IFavoritesRepository
import javax.inject.Inject

class ToggleFavoriteUseCase @Inject constructor(
    private val favoritesRepository: IFavoritesRepository
) {
    suspend operator fun invoke(course: Course) {
        if (favoritesRepository.isFavorite(course.id)) {
            favoritesRepository.removeFromFavorites(course.id)
        } else {
            favoritesRepository.addToFavorites(course)
        }
    }
}

