package com.example.impl

import com.example.api.BottomNavApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object BottomNavModule {
    @Provides
    @Singleton
    fun provideBottomNavApi(): BottomNavApi = BottomNavApiImpl()
}
