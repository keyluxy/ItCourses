package com.example.core.domain.usecase

import com.example.core.domain.model.Course
import com.example.core.domain.repository.IFavoritesRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetFavoritesUseCase @Inject constructor(
    private val favoritesRepository: IFavoritesRepository
) {
    operator fun invoke(): Flow<List<Course>> {
        return favoritesRepository.observeFavorites()
    }
}

