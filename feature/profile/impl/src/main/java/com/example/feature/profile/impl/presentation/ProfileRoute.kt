package com.example.feature.profile.impl.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import com.example.core.R as coreR
import com.example.feature.profile.impl.presentation.viewmodel.ProfileViewModel

@Composable
fun ProfileRoute(
    viewModel: ProfileViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsState()
    ProfileScreen(uiState = uiState)
}

@Composable
internal fun ProfileScreen(
    uiState: ProfileUiState
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(colorResource(id = coreR.color.screen_background))
            .padding(16.dp)
    ) {
        Text(
            text = uiState.title,
            fontSize = 24.sp,
            color = colorResource(id = coreR.color.white),
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(bottom = 24.dp)
        )

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(16.dp))
                .background(colorResource(id = coreR.color.field_background))
                .padding(16.dp)
        ) {
            Column {
                ProfileMenuItem(
                    text = "Написать в поддержку",
                    onClick = { /* TODO */ },
                    modifier = Modifier.padding(bottom = 16.dp)
                )
                ProfileMenuItem(
                    text = "Настройки",
                    onClick = { /* TODO */ },
                    modifier = Modifier.padding(bottom = 16.dp)
                )
                ProfileMenuItem(
                    text = "Выйти из аккаунта",
                    onClick = { /* TODO */ }
                )
            }
        }

        Text(
            text = "Ваши курсы",
            fontSize = 24.sp,
            color = colorResource(id = coreR.color.white),
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(top = 32.dp, bottom = 16.dp)
        )

        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            items(uiState.userCourses) { course ->
                UserCourseCard(
                    course = course,
                    onCardClick = { /* TODO */ },
                    onFavoriteClick = { /* TODO */ }
                )
            }
        }
    }
}

@Composable
private fun ProfileMenuItem(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    val interactionSource = remember { MutableInteractionSource() }
    
    Row(
        modifier = modifier
            .fillMaxWidth()
            .clickable(
                interactionSource = interactionSource,
                indication = null
            ) { onClick() },
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = text,
            color = colorResource(id = coreR.color.white),
            fontSize = 16.sp,
            fontWeight = FontWeight.Normal
        )
        Text(
            text = "→",
            color = colorResource(id = coreR.color.white),
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold
        )
    }
}

