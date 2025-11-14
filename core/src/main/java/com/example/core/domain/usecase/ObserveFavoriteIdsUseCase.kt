package com.example.core.domain.usecase

import com.example.core.domain.repository.IFavoritesRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ObserveFavoriteIdsUseCase @Inject constructor(
    private val favoritesRepository: IFavoritesRepository
) {
    operator fun invoke(): Flow<Set<Int>> {
        return favoritesRepository.observeFavoriteIds()
    }
}

