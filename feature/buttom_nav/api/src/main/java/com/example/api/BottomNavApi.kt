package com.example.api

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController

interface BottomNavApi {
    val items: List<BottomNavItem>
    val startDestination: String

    fun NavGraphBuilder.registerGraph(navController: NavHostController)

    @Composable
    fun BottomNavigationBar(
        navController: NavHostController,
        modifier: Modifier
    )
}
