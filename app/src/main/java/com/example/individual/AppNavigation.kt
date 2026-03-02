package com.example.individual

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

@Composable
fun AppNavigation() {
    val navController = rememberNavController()

    NavHost(navController, startDestination = "dashboard") {

        composable("dashboard") { DashboardScreen(navController) }
        composable("login") { LoginScreen(navController) }
        composable("signup") { SignUpScreen(navController) }
        composable("main") { MainScreen(navController) }
        composable("categorization") { MyCategorizationScreen(navController) }

        // Interest screens using reusable component
        composable("interest_input/short_term") {
            InterestInputScreen(navController, "short_term", "Short Term Goals")
        }
        composable("interest_input/long_term") {
            InterestInputScreen(navController, "long_term", "Long Term Goals")
        }
        composable("interest_input/wishlist") {
            InterestInputScreen(navController, "wishlist", "Wishlist")
        }
        composable("shopping_list") {
            InterestInputScreen(navController, "shopping_list", "Shopping List")
        }

        composable("settings_feedback") { SettingsFeedbackScreen(navController) }
    }
}