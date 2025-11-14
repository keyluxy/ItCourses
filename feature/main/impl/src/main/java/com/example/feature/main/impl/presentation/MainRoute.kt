package com.example.feature.main.impl.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import com.example.core.R as coreR
import com.example.feature.main.impl.presentation.viewmodel.MainViewModel

@Composable
fun MainRoute(
    viewModel: MainViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsState()
    MainScreen(
        uiState = uiState,
        onSortClick = { viewModel.sortCoursesByPublishDate() },
        onFavoriteClick = { course -> viewModel.toggleFavorite(course) }
    )
}

@Composable
internal fun MainScreen(
    uiState: MainUiState,
    onSortClick: () -> Unit = {},
    onFavoriteClick: (com.example.core.network.data.Course) -> Unit = {}
) {
    var searchText by remember { mutableStateOf("") }
    val filterInteractionSource = remember { MutableInteractionSource() }
    val sortInteractionSource = remember { MutableInteractionSource() }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(colorResource(id = coreR.color.screen_background))
    ) {
        if (uiState.isLoading) {
            // Показываем loader по центру экрана
            CircularProgressIndicator(
                modifier = Modifier.align(Alignment.Center),
                color = colorResource(id = coreR.color.green)
            )
        } else {
            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.spacedBy(16.dp),
                contentPadding = androidx.compose.foundation.layout.PaddingValues(16.dp)
            ) {

                // 🔍 Поиск + фильтр
                item {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(8.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        // Поисковая строка
                        Box(
                            modifier = Modifier
                                .weight(1f)
                                .height(56.dp)
                                .clip(RoundedCornerShape(28.dp))
                                .background(colorResource(id = coreR.color.field_background))
                                .padding(horizontal = 16.dp),
                            contentAlignment = Alignment.CenterStart
                        ) {
                            Row(
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.spacedBy(8.dp),
                                modifier = Modifier.fillMaxWidth()
                            ) {
                                Icon(
                                    painter = painterResource(id = coreR.drawable.ic_search),
                                    contentDescription = "Поиск",
                                    modifier = Modifier.size(24.dp),
                                    tint = colorResource(id = coreR.color.placeholder_color)
                                )

                                BasicTextField(
                                    value = searchText,
                                    onValueChange = { searchText = it },
                                    modifier = Modifier.weight(1f),
                                    textStyle = TextStyle(
                                        color = colorResource(id = coreR.color.white),
                                        fontSize = 16.sp
                                    ),
                                    singleLine = true,
                                    decorationBox = { inner ->
                                        if (searchText.isEmpty()) {
                                            Text(
                                                text = stringResource(coreR.string.searchCourses),
                                                color = colorResource(id = coreR.color.placeholder_color),
                                                fontSize = 16.sp
                                            )
                                        }
                                        inner()
                                    }
                                )
                            }
                        }

                        // Фильтр
                        Box(
                            modifier = Modifier
                                .size(56.dp)
                                .clip(RoundedCornerShape(28.dp))
                                .background(colorResource(id = coreR.color.field_background))
                                .clickable(
                                    interactionSource = filterInteractionSource,
                                    indication = null
                                ) {},
                            contentAlignment = Alignment.Center
                        ) {
                            Icon(
                                painter = painterResource(id = coreR.drawable.ic_fillter),
                                contentDescription = "Фильтр",
                                modifier = Modifier.size(24.dp),
                                tint = colorResource(id = coreR.color.white)
                            )
                        }
                    }
                }

                // 📌 Текст сортировки
                item {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable(
                                interactionSource = sortInteractionSource,
                                indication = null
                            ) { onSortClick() },
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Spacer(modifier = Modifier.weight(1f))

                        Text(
                            text = "По дате добавления",
                            color = colorResource(id = coreR.color.green),
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Medium
                        )

                        Spacer(modifier = Modifier.width(8.dp))

                        Icon(
                            painter = painterResource(id = coreR.drawable.ic_two_filters),
                            contentDescription = null,
                            modifier = Modifier.size(16.dp),
                            tint = colorResource(id = coreR.color.green)
                        )
                    }
                }

                // 🟩 Карточки
                itemsIndexed(
                    items = uiState.courses,
                    key = { _, course -> course.id }
                ) { _, course ->
                    // Используем ID курса для определения картинки, чтобы картинка была привязана к курсу, а не к позиции
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
                    onFavoriteClick = { onFavoriteClick(course) }
                )
                }
            }
        }
    }
}

