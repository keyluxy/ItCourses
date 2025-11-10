package com.example.impl.di

import com.example.api.repository.IAuthRepository
import com.example.impl.data.repository.AuthRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class AuthBindingModule {

    @Binds
    @Singleton
    abstract fun provideAuth(impl: AuthRepositoryImpl): IAuthRepository
}