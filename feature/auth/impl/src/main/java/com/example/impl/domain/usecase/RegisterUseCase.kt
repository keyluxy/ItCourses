package com.example.impl.domain.usecase

import com.example.api.model.AuthUser
import com.example.api.repository.IAuthRepository
import javax.inject.Inject

class RegisterUseCase @Inject constructor(
    private val repository: IAuthRepository
) {
    suspend operator fun invoke(email: String, password: String): Result<AuthUser> {
        return repository.register(email, password)
    }
}