package com.example.feature.favorites.impl.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.sp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import com.example.core.R as coreR
import com.example.feature.favorites.impl.presentation.viewmodel.FavoritesViewModel

@Composable
fun FavoritesRoute(
    viewModel: FavoritesViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsState()
    FavoritesScreen(uiState = uiState)
}

@Composable
internal fun FavoritesScreen(
    uiState: FavoritesUiState
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(colorResource(id = coreR.color.screen_background)),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = uiState.title,
            fontSize = 24.sp,
            color = colorResource(id = coreR.color.white)
        )
    }
}

