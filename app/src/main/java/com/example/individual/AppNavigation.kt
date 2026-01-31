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
        startDestination = "dashboard" // Dashboard is the first screen
    ) {

        // Dashboard Screen
        composable("dashboard") {
            DashboardScreen(navController)
        }

        // Login Screen
        composable("login") {
            LoginScreen(navController)
        }

        // Sign Up Screen
        composable("signup") {
            SignUpScreen(navController)
        }

        // Main screen (your existing app screen)
        composable("main") {
            MainScreen(navController)
        }

        // Categorization screen
        composable("categorization") {
            MyCategorizationScreen(navController)
        }

        // Category-specific screens
        composable("short_term") {
            ShortTermScreen(navController)
        }

        composable("long_term") {
            LongTermScreen(navController)
        }

        composable("wishlist") {
            WishlistScreen(navController)
        }

        // Settings & Feedback screen
        composable("settings_feedback") {
            SettingsFeedbackScreen(navController)
        }
    }
}
