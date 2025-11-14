package com.example.feature.profile.impl.presentation

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.core.R as coreR
import com.example.core.network.data.UserCourse

@Composable
fun UserCourseCard(
    course: UserCourse,
    onCardClick: () -> Unit = {},
    onFavoriteClick: () -> Unit = {}
) {
    val cardInteractionSource = remember { MutableInteractionSource() }
    val favoriteInteractionSource = remember { MutableInteractionSource() }
    var isFavorite by remember { mutableStateOf(true) } // Курсы в профиле всегда в избранном

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(236.dp)
            .clip(RoundedCornerShape(16.dp))
            .background(colorResource(id = coreR.color.course_card_dark))
            .clickable(
                interactionSource = cardInteractionSource,
                indication = null
            ) { onCardClick() }
    ) {
        Column {
            // Верхняя часть с изображением
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(114.dp)
            ) {
                // Изображение курса
                Image(
                    painter = painterResource(id = course.imageResId),
                    contentDescription = null,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(114.dp)
                        .clip(RoundedCornerShape(12.dp)),
                    contentScale = ContentScale.Crop
                )

                // Контейнеры с рейтингом и датой в нижнем левом углу
                Row(
                    modifier = Modifier
                        .align(Alignment.BottomStart)
                        .padding(start = 12.dp, bottom = 12.dp),
                    horizontalArrangement = Arrangement.spacedBy(4.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    // Контейнер с рейтингом
                    Box(
                        modifier = Modifier
                            .width(46.dp)
                            .height(22.dp)
                            .clip(RoundedCornerShape(12.dp))
                            .background(colorResource(id = coreR.color.blur))
                    ) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.spacedBy(4.dp),
                            modifier = Modifier.padding(horizontal = 6.dp)
                        ) {
                            Icon(
                                painter = painterResource(id = coreR.drawable.ic_star),
                                contentDescription = null,
                                modifier = Modifier.size(12.dp),
                                tint = Color.Unspecified
                            )
                            Text(
                                text = course.rate,
                                color = Color.White,
                                fontSize = 12.sp,
                                fontWeight = FontWeight.Medium
                            )
                        }
                    }

                    // Контейнер с датой
                    Box(
                        modifier = Modifier
                            .width(87.dp)
                            .height(22.dp)
                            .clip(RoundedCornerShape(12.dp))
                            .background(colorResource(id = coreR.color.blur))
                    ) {
                        Text(
                            text = course.startDate,
                            color = Color.White,
                            fontSize = 12.sp,
                            modifier = Modifier.align(Alignment.Center)
                        )
                    }
                }

                // Иконка закладки в правом верхнем углу
                Box(
                    modifier = Modifier
                        .align(Alignment.TopEnd)
                        .padding(8.dp)
                        .size(28.dp)
                        .background(
                            color = colorResource(coreR.color.box),
                            RoundedCornerShape(20.dp)
                        )
                        .clickable(
                            interactionSource = favoriteInteractionSource,
                            indication = null
                        ) {
                            isFavorite = !isFavorite
                            onFavoriteClick()
                        },
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        painter = painterResource(
                            id = if (isFavorite) coreR.drawable.ic_filter_done else coreR.drawable.ic_favorities
                        ),
                        contentDescription = "Добавить в избранное",
                        tint = if (isFavorite) Color.Unspecified else Color.White,
                        modifier = Modifier.size(13.dp)
                    )
                }
            }

            // Нижняя часть с информацией
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            ) {
                Column {
                    // Название курса
                    Text(
                        text = course.title,
                        color = colorResource(id = coreR.color.white),
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Medium,
                        modifier = Modifier.padding(bottom = 8.dp)
                    )

                    // Прогресс и количество уроков
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(bottom = 4.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        Text(
                            text = "${course.progress}%",
                            color = colorResource(id = coreR.color.green),
                            fontSize = 14.sp,
                            fontWeight = FontWeight.Medium
                        )
                        
                        LinearProgressIndicator(
                            progress = { course.progress / 100f },
                            modifier = Modifier
                                .weight(1f)
                                .height(4.dp)
                                .clip(RoundedCornerShape(2.dp)),
                            color = colorResource(id = coreR.color.green),
                            trackColor = colorResource(id = coreR.color.field_background)
                        )
                    }

                    // Количество уроков
                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = "${course.currentLesson}/",
                            color = Color(0xFFA0A0A0), // Серый цвет
                            fontSize = 12.sp,
                            fontWeight = FontWeight.Normal
                        )
                        Text(
                            text = "${course.totalLessons} уроков",
                            color = colorResource(id = coreR.color.green),
                            fontSize = 12.sp,
                            fontWeight = FontWeight.Normal
                        )
                    }
                }
            }
        }
    }
}

