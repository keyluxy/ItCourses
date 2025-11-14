package com.example.core.domain.usecase

import com.example.core.domain.model.Course
import com.example.core.domain.repository.ICoursesRepository
import javax.inject.Inject

class GetCoursesUseCase @Inject constructor(
    private val coursesRepository: ICoursesRepository
) {
    suspend operator fun invoke(): Result<List<Course>> {
        return coursesRepository.getCourses()
    }
}

