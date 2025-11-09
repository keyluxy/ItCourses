package com.example.api.repository

import com.example.api.model.AuthUser
import kotlinx.coroutines.flow.Flow

interface IAuthRepository {
    suspend fun register(email: String, password: String): Result<AuthUser>
    suspend fun login(email: String, password: String): Result<AuthUser>
    suspend fun observeCurrentUser(): Flow<AuthUser?>
}