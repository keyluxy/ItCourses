package com.example.core.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.core.database.dao.FavoriteCourseDao
import com.example.core.database.dao.UserDao
import com.example.core.database.entities.FavoriteCourseEntity
import com.example.core.database.entities.UserEntity

@Database(
    entities = [UserEntity::class, FavoriteCourseEntity::class],
    version = 2,
)
abstract class AppDatabase: RoomDatabase() {
    abstract fun getUserDao(): UserDao
    abstract fun getFavoriteCourseDao(): FavoriteCourseDao
}