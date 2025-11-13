// app/src/main/java/com/example/itcourses/ui/MainScreen.kt
package com.example.itcourses.ui

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.api.BottomNavApi
import com.example.api.navigation.AuthRoutes
import com.example.impl.presentation.ui.login.LoginScreen
import com.example.impl.presentation.ui.register.RegistrationScreen

@Composable
fun MainScreen(
    navController: NavHostController,
    bottomNavApi: BottomNavApi
) {
    val currentBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = currentBackStackEntry?.destination?.route

    val bottomNavRoutes = remember(bottomNavApi.items) {
        bottomNavApi.items.map { it.route }.toSet()
    }
    val showBottomBar = currentRoute in bottomNavRoutes

    Scaffold(
        bottomBar = {
            if (showBottomBar) {
                bottomNavApi.BottomNavigationBar(
                    navController = navController,
                    modifier = Modifier
                )
            }
        }
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = AuthRoutes.LOGIN,
            modifier = Modifier.padding(innerPadding)
        ) navGraph@{
            composable(AuthRoutes.LOGIN) {
                LoginScreen(
                    onLoginSuccess = {
                        navController.navigate(bottomNavApi.startDestination) {
                            popUpTo(AuthRoutes.LOGIN) { inclusive = true }
                        }
                    },
                    onNavigateToRegister = {
                        navController.navigate(AuthRoutes.REGISTER)
                    }
                )
            }
            composable(AuthRoutes.REGISTER) {
                RegistrationScreen(
                    onRegisterSuccess = {
                        navController.navigate(bottomNavApi.startDestination) {
                            popUpTo(AuthRoutes.REGISTER) { inclusive = true }
                        }
                    },
                    onNavigateToLogin = {
                        navController.navigate(AuthRoutes.LOGIN)
                    }
                )
            }

            bottomNavApi.run {
                this@navGraph.registerGraph(navController)
            }
        }
    }
}
