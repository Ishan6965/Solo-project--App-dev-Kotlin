package com.example.individual

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

@Composable
fun AppNavigation() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = "main"
    ) {

        // Main screen
        composable("main") {
            MainScreen(navController)
        }

        // Categorization screen
        composable("categorization") {
            MyCategorizationScreen(navController)
        }

        // Category screens
        composable("short_term") {
            ShortTermScreen(navController)
        }

        composable("long_term") {
            LongTermScreen(navController)
        }

        composable("wishlist") {
            WishlistScreen(navController)
        }

        composable("settings_feedback") {
            SettingsFeedbackScreen(navController)
        }

    }
}
