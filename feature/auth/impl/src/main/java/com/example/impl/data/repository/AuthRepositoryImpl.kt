package com.example.impl.data.repository

import com.example.api.model.AuthUser
import com.example.api.repository.IAuthRepository
import com.example.core.database.dao.UserDao
import com.example.core.database.entities.UserEntity
import com.example.impl.data.mapper.AuthMapper
import com.example.impl.utils.passwordHash
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(
    private val userDao: UserDao,
) : IAuthRepository {
    override suspend fun register(email: String, password: String): Result<AuthUser> {
        return try {

            if (email.isBlank() || password.isBlank()) {
                return Result.failure(IllegalArgumentException("Email и пароль некорректны"))
            }

            val existing = userDao.findUserByEmail(email)
            if (existing != null)
                return Result.failure(IllegalStateException("Пользователь с таким email уже существует"))

            val hashed = passwordHash(password)
            val insertedId = userDao.addUser(UserEntity(email = email, passwordHash = hashed)).toInt()
            val saved = userDao.findUserByEmail(email) ?: throw IllegalStateException("Не удалось сохранить пользователя")
            Result.success(AuthMapper.toDomain(saved))

        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    override suspend fun login(email: String, password: String): Result<AuthUser> {
        return try {
            val hashed = passwordHash(password)
            val user = userDao.authenticateUser(email = email, passwordHash = hashed)
                ?: return Result.failure(IllegalStateException("неверный email или пароль"))
            Result.success(AuthMapper.toDomain(user))
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    override suspend fun observeCurrentUser(): Flow<AuthUser?> =
        userDao.getALlUsers().map { list -> list.firstOrNull()?.let { AuthMapper.toDomain(it) } }

}