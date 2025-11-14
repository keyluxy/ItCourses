package com.example.core.di

import android.content.Context
import androidx.room.Room
import com.example.core.database.AppDatabase
import com.example.core.database.dao.UserDao
import com.example.core.data.local.FavoriteCourseDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    fun provideDatabase(@ApplicationContext context: Context) =
        Room.databaseBuilder(
            context,
            AppDatabase::class.java,
            "itcourses.db"
        ).build()

    @Provides
    @Singleton
    fun provideUserDao(db: AppDatabase) = db.getUserDao()

    @Provides
    @Singleton
    fun provideFavoriteCourseDao(db: AppDatabase) = db.getFavoriteCourseDao()
}