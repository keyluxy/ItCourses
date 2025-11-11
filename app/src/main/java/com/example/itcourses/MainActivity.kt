package com.example.itcourses

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.api.navigation.AuthRoutes
import com.example.impl.presentation.ui.login.LoginScreen
import com.example.impl.presentation.ui.register.RegistrationScreen
import com.example.itcourses.ui.MainScreen
import com.example.itcourses.ui.theme.ItCoursesTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ItCoursesTheme {
                val navController = rememberNavController()

                NavHost(
                    navController = navController,
                    startDestination = AuthRoutes.LOGIN
                ) {
                    composable(AuthRoutes.LOGIN) {
                        LoginScreen(navController = navController)
                    }
                    composable(AuthRoutes.REGISTER) {
                        RegistrationScreen(navController = navController)
                    }
                    composable(AuthRoutes.MAIN) {
                        MainScreen()
                    }
                }
            }
        }
    }
}