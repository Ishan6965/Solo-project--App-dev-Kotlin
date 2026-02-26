package com.example.individual

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument

@Composable
fun AppNavigation() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = "dashboard" // Dashboard is first screen
    ) {

        // Dashboard screen (first screen user sees)
        composable("dashboard") {
            DashboardScreen(navController)
        }

        // Login screen
        composable("login") {
            LoginScreen(navController)
        }

        // Sign Up screen
        composable("signup") {
            SignUpScreen(navController)
        }

        // Main screen (your main app screen)
        composable("main") {
            MainScreen(navController)
        }

        // Categorization screen (after login / main)
        composable("categorization") {
            MyCategorizationScreen(navController)
        }

        // Interest Input screen with category argument
        composable(
            route = "interest_input/{category}",
            arguments = listOf(
                navArgument("category") {
                    type = NavType.StringType
                }
            )
        ) { backStackEntry ->
            val category = backStackEntry.arguments?.getString("category") ?: ""
            InterestInputScreen(navController, category)
        }
    }
}