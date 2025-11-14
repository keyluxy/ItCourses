package com.example.feature.favorites.impl.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import com.example.core.R as coreR
import com.example.feature.favorites.impl.presentation.viewmodel.FavoritesViewModel
import com.example.feature.main.impl.presentation.CourseCard

@Composable
fun FavoritesRoute(
    viewModel: FavoritesViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsState()
    FavoritesScreen(
        uiState = uiState,
        onRemoveFavorite = { courseId -> viewModel.removeFavorite(courseId) }
    )
}

@Composable
internal fun FavoritesScreen(
    uiState: FavoritesUiState,
    onRemoveFavorite: (Int) -> Unit = {}
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(colorResource(id = coreR.color.screen_background))
    ) {
        if (uiState.isLoading) {
            CircularProgressIndicator(
                modifier = Modifier.align(Alignment.Center),
                color = colorResource(id = coreR.color.green)
            )
        } else if (uiState.courses.isEmpty()) {
            Text(
                text = "Нет избранных курсов",
                fontSize = 24.sp,
                color = colorResource(id = coreR.color.white),
                modifier = Modifier.align(Alignment.Center)
            )
        } else {
            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.spacedBy(16.dp),
                contentPadding = androidx.compose.foundation.layout.PaddingValues(16.dp)
            ) {
                item {
                    Text(
                        text = uiState.title,
                        fontSize = 24.sp,
                        color = colorResource(id = coreR.color.white),
                        modifier = Modifier.padding(bottom = 16.dp)
                    )
                }

                itemsIndexed(
                    items = uiState.courses,
                    key = { _, course -> course.id }
                ) { _, course ->
                    // Используем ID курса для определения картинки, а не индекс в списке избранного
                    // Равномерно распределяем все три картинки по остатку от деления на 3
                    val imageResId = when (course.id % 3) {
                        1 -> coreR.drawable.ic_first_image
                        2 -> coreR.drawable.ic_second_image
                        0 -> coreR.drawable.ic_three_image
                        else -> coreR.drawable.ic_first_image
                    }

                    CourseCard(
                        course = course,
                        imageResId = imageResId,
                        onCardClick = {},
                        onFavoriteClick = { onRemoveFavorite(course.id) }
                    )
                }
            }
        }
    }
}

