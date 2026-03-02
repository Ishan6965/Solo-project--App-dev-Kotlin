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
fun MyCategorizationScreen(navController: NavController) {
    Column(modifier = Modifier.fillMaxSize()) {

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(120.dp)
                .background(Color.LightGray),
            contentAlignment = Alignment.Center
        ) {
            Text("Choose your categorization", fontSize = 28.sp)
        }

        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color(0xFFFFF59D))
                .padding(16.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Button(
                onClick = { navController.navigate("interest_input/short_term") },
                modifier = Modifier.fillMaxWidth()
            ) { Text("Short Term Goals") }

            Spacer(modifier = Modifier.height(20.dp))

            Button(
                onClick = { navController.navigate("interest_input/long_term") },
                modifier = Modifier.fillMaxWidth()
            ) { Text("Long Term Goals") }

            Spacer(modifier = Modifier.height(20.dp))

            Button(
                onClick = { navController.navigate("interest_input/wishlist") },
                modifier = Modifier.fillMaxWidth()
            ) { Text("Wishlist") }
        }
    }
}