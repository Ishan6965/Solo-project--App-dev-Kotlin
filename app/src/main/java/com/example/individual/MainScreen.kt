package com.example.individual

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

@Composable
fun MainScreen(navController: NavController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFE3F2FD))
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
        Spacer(modifier = Modifier.height(40.dp))

        Button(
            onClick = { navController.navigate("categorization") },
            modifier = Modifier.fillMaxWidth()
        ) { Text("Categorize Goals", fontSize = 20.sp) }

        Spacer(modifier = Modifier.height(20.dp))

        Button(
            onClick = { navController.navigate("settings_feedback") },
            modifier = Modifier.fillMaxWidth()
        ) { Text("Settings & Feedback", fontSize = 20.sp) }

        Spacer(modifier = Modifier.height(20.dp))

        Button(
            onClick = { navController.navigate("shopping_list") },
            modifier = Modifier.fillMaxWidth()
        ) { Text("Shopping List", fontSize = 20.sp) }
    }
}