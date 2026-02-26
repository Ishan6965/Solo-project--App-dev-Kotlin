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

@Composable
fun SettingsFeedbackScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFFFC107)) // Bright yellow background
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        // Settings Button
        Button(
            onClick = { /* Does nothing for now */ },
            modifier = Modifier
                .fillMaxWidth()
                .height(120.dp)
        ) {
            Text("Settings", fontSize = 28.sp)
        }

        Spacer(modifier = Modifier.height(50.dp))

        // Feedback Button
        Button(
            onClick = { /* Does nothing for now */ },
            modifier = Modifier
                .fillMaxWidth()
                .height(120.dp)
        ) {
            Text("Feedback", fontSize = 28.sp)
        }
    }
}