package com.example.core.domain.usecase

import com.example.core.domain.repository.IFavoritesRepository
import javax.inject.Inject

class RemoveFavoriteUseCase @Inject constructor(
    private val favoritesRepository: IFavoritesRepository
) {
    suspend operator fun invoke(courseId: Int) {
        favoritesRepository.removeFromFavorites(courseId)
    }
}

