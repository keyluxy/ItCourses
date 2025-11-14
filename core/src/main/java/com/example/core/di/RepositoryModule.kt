package com.example.core.di

import com.example.core.data.repository.CoursesRepositoryImpl
import com.example.core.data.repository.FavoritesRepositoryImpl
import com.example.core.domain.repository.ICoursesRepository
import com.example.core.domain.repository.IFavoritesRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun bindCoursesRepository(
        coursesRepositoryImpl: CoursesRepositoryImpl
    ): ICoursesRepository

    @Binds
    @Singleton
    abstract fun bindFavoritesRepository(
        favoritesRepositoryImpl: FavoritesRepositoryImpl
    ): IFavoritesRepository
}

