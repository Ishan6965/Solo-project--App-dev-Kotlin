package com.example.individual

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

@Composable
fun SettingsFeedbackScreen(navController: NavController) {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFFFF9C4)) // light yellow
    ) {

        // Top title bar
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.White)
                .padding(vertical = 20.dp),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = "Settings and Feedback",
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold
            )
        }

        Spacer(modifier = Modifier.height(190.dp))

        // Buttons
        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Button(
                onClick = { /* navigate to settings later */ },
                modifier = Modifier
                    .width(220.dp)
                    .height(95.dp)
            ) {
                Text(text = "Settings", fontSize = 28.sp)
            }

            Spacer(modifier = Modifier.height(110.dp))

            Button(
                onClick = { /* navigate to feedback later */ },
                modifier = Modifier
                    .width(225.dp)
                    .height(100.dp)
            ) {
                Text(text = "Give Feedback", fontSize = 27.sp)
            }

        }
    }
}
