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
        startDestination = "dashboard"
    ) {

        composable("dashboard") {
            DashboardScreen(navController)
        }

        composable("login") {
            LoginScreen(navController)
        }

        composable("signup") {
            SignUpScreen(navController)
        }

        composable("main") {
            MainScreen(navController)
        }

        composable("categorization") {
            MyCategorizationScreen(navController)
        }

        // NEW: All category inputs
        composable("interest/{category}") { backStackEntry ->
            val category = backStackEntry.arguments?.getString("category") ?: "unknown"
            InterestInputScreen(navController, category)
        }

        // Settings & Feedback
        composable("settings_feedback") {
            SettingsFeedbackScreen(navController)
        }
    }
}