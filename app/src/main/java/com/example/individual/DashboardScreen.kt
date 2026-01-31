package com.example.individual

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.individual.R

@Composable
fun DashboardScreen(navController: NavController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Yellow) // yellow background for the screen
    ) {

        // Top Green bar with Dashboard text
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(100.dp)
                .background(Color(0xFF4CAF50)), // green
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = "Dashboard",
                fontSize = 28.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White
            )
        }

        Spacer(modifier = Modifier.height(50.dp))

        // Center image
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f),
            contentAlignment = Alignment.Center
        ) {
            // Replace with your own image resource
            Image(
                painter = painterResource(id = R.drawable.pic1),
                contentDescription = "Dashboard Image",
                modifier = Modifier
                    .size(200.dp)
            )
        }

        Spacer(modifier = Modifier.height(50.dp))

        // Login/Register button at bottom
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 50.dp),
            contentAlignment = Alignment.Center
        ) {
            Button(
                onClick = { navController.navigate("login") },
            ) {
                Text(
                    text = "Login / Register",
                    fontSize = 24.sp,
                    color = Color.Red
                )
            }
        }
    }
}
