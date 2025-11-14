package com.example.impl

import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.api.BottomNavApi
import com.example.api.BottomNavItem
import com.example.core.R as coreR
import com.example.feature.favorites.api.FavoritesDestination
import com.example.feature.favorites.impl.presentation.FavoritesRoute
import com.example.feature.main.api.MainDestination
import com.example.feature.main.impl.presentation.MainRoute
import com.example.feature.profile.api.ProfileDestination
import com.example.feature.profile.impl.presentation.ProfileRoute
import javax.inject.Inject

class BottomNavApiImpl @Inject constructor() : BottomNavApi {

    override val items: List<BottomNavItem> = listOf(
        BottomNavItem(
            route = MainDestination.ROUTE,
            label = MainDestination.TITLE,
            iconResId = coreR.drawable.ic_main
        ),
        BottomNavItem(
            route = FavoritesDestination.ROUTE,
            label = FavoritesDestination.TITLE,
            iconResId = coreR.drawable.ic_favorities
        ),
        BottomNavItem(
            route = ProfileDestination.ROUTE,
            label = ProfileDestination.TITLE,
            iconResId = coreR.drawable.ic_account
        )
    )

    override val startDestination: String = MainDestination.ROUTE

    override fun NavGraphBuilder.registerGraph(navController: NavHostController) {
        composable(route = MainDestination.ROUTE) {
            MainRoute()
        }
        composable(route = FavoritesDestination.ROUTE) {
            FavoritesRoute()
        }
        composable(route = ProfileDestination.ROUTE) {
            ProfileRoute()
        }
    }

    @Composable
    override fun BottomNavigationBar(
        navController: NavHostController,
        modifier: Modifier
    ) {
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute = navBackStackEntry?.destination?.route

        NavigationBar(
            modifier = modifier,
            containerColor = BottomNavDefaults.containerColor,
            tonalElevation = 16.dp
        ) {
            items.forEach { item ->
                val isSelected = currentRoute == item.route
                NavigationBarItem(
                    selected = isSelected,
                    onClick = {
                        if (!isSelected) {
                            navController.navigate(item.route) {
                                launchSingleTop = true
                                restoreState = true
                                popUpTo(navController.graph.startDestinationId) {
                                    saveState = true
                                }
                            }
                        }
                    },
                    icon = {
                        Icon(
                            painter = painterResource(id = item.iconResId),
                            contentDescription = item.label,
                            tint = if (isSelected) BottomNavDefaults.selectedIconColor else BottomNavDefaults.unselectedIconColor,
                            modifier = Modifier.width(24.dp).height(24.dp)
                        )
                    },
                    label = {
                        Text(
                            text = item.label,
                            color = if (isSelected) BottomNavDefaults.selectedTextColor else BottomNavDefaults.unselectedTextColor,
                            fontWeight = if (isSelected) FontWeight.Medium else FontWeight.Normal,
                            fontSize = 10.sp,
                            maxLines = 1
                        )
                    },
                    colors = NavigationBarItemDefaults.colors(
                        indicatorColor = BottomNavDefaults.indicatorColor,
                        selectedIconColor = BottomNavDefaults.selectedIconColor,
                        unselectedIconColor = BottomNavDefaults.unselectedIconColor,
                        selectedTextColor = BottomNavDefaults.selectedTextColor,
                        unselectedTextColor = BottomNavDefaults.unselectedTextColor
                    )
                )
            }
        }
    }
}

private object BottomNavDefaults {
    val containerColor = Color(0xFF24252A)
    val indicatorColor = Color(0xFF32333A)
    val selectedIconColor = Color(0xFF12B956)
    val selectedTextColor = selectedIconColor
    val unselectedIconColor = Color(0xFFA0A0A0)
    val unselectedTextColor = unselectedIconColor
}
