// app/src/main/java/com/example/itcourses/MainActivity.kt
package com.example.itcourses

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.navigation.compose.rememberNavController
import com.example.api.BottomNavApi
import com.example.itcourses.ui.MainScreen
import com.example.itcourses.ui.theme.ItCoursesTheme
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @Inject
    lateinit var bottomNavApi: BottomNavApi

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ItCoursesTheme {
                val navController = rememberNavController()
                MainScreen(
                    navController = navController,
                    bottomNavApi = bottomNavApi
                )
            }
        }
    }
}