package com.example.core.di

import com.example.core.network.domain.CoursesRepository
import com.example.core.network.domain.ICoursesRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class CoursesRepositoryModule {

    @Binds
    @Singleton
    abstract fun provideCoursesRepository(impl: CoursesRepository): ICoursesRepository
}

