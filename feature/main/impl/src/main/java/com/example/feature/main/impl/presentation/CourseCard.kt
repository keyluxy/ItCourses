package com.example.feature.main.impl.presentation

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
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
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.core.R as coreR
import com.example.core.network.data.Course

@Composable
fun CourseCard(
    course: Course,
    imageResId: Int,
    onCardClick: () -> Unit = {},
    onFavoriteClick: () -> Unit = {}
) {
    val cardInteractionSource = remember { MutableInteractionSource() }
    val favoriteInteractionSource = remember { MutableInteractionSource() }
    var isFavorite by remember { mutableStateOf(course.hasLike) }

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
                    painter = painterResource(id = imageResId),
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
                            horizontalArrangement = Arrangement.spacedBy(10.dp)
                        ) {
                            Icon(
                                painter = painterResource(id = coreR.drawable.ic_star),
                                contentDescription = null,
                                modifier = Modifier
                                    .size(12.dp)
                                    .padding(start = 6.dp),
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
                        painter = painterResource(id = coreR.drawable.ic_favorities),
                        contentDescription = "Добавить в избранное",
                        tint = if (isFavorite) colorResource(id = coreR.color.green) else Color.White,
                        modifier = Modifier.size(28.dp)
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
                    // Название курса (заголовок)
                    Text(
                        text = course.title,
                        color = colorResource(id = coreR.color.white),
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Medium,
                        modifier = Modifier.padding(bottom = 8.dp)
                    )

                    // Описание курса (основной текст)
                    Text(
                        text = course.text,
                        color = colorResource(id = coreR.color.white),
                        style = TextStyle(
                            fontSize = 12.sp,
                            fontWeight = FontWeight.Normal,
                            lineHeight = 12.sp
                        ),
                        maxLines = 2,
                        modifier = Modifier.padding(bottom = 12.dp)
                    )

                    // Цена и кнопка "Подробнее"
                    Row(
                        modifier = Modifier.fillMaxWidth().height(IntrinsicSize.Min),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        // Цена слева
                        Text(
                            text = "${course.price} Р",
                            color = colorResource(id = coreR.color.white),
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Bold
                        )

                        Text(
                            text = "Подробнее →",
                            color = colorResource(id = coreR.color.green),
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Medium,
                            modifier = Modifier.align(Alignment.CenterVertically)
                        )
                    }
                }
            }
        }
    }
}


@Preview(showBackground = true, backgroundColor = 0xFF000000)
@Composable
fun CourseCardPreview() {
    val testCourse = Course(
        id = 1,
        title = "Разработка мобильных приложений",
        text = "Курс по созданию приложений для Android и iOS с использованием Kotlin и Swift.",
        rate = "4.8",
        startDate = "15.11.2025",
        price = "15000",
        hasLike = false,
        publishDate = ""
    )
    CourseCard(
        course = testCourse,
        imageResId = coreR.drawable.img_first,
        onCardClick = { /* do nothing */ },
        onFavoriteClick = { /* do nothing */ }
    )
}
