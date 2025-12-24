package com.example.individual_project

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun MyCategorizationScreen() {
    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        // Top gray section (small, flat)
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(130.dp)
                .background(Color.LightGray),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = "Choose your categorization",
                fontSize = 31.sp,
                color = Color.Black
            )
        }

        // Yellow section filling remaining space
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color(0xFFFFEB3B)) // yellow
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(170.dp)) // push buttons lower

            // First row
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                CategoryButton(text = "Short term Goals", modifier = Modifier.weight(1f))
                CategoryButton(text = "Long term Goals", modifier = Modifier.weight(1f))
            }

            Spacer(modifier = Modifier.height(75.dp))

            // Second row
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                CategoryButton(text = "Wishlist", modifier = Modifier.weight(1f))
                CategoryButton(text = "Shopping List", modifier = Modifier.weight(1f))
            }

            Spacer(modifier = Modifier.height(70.dp))

            // Third row
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                CategoryButton(text = "Notes", modifier = Modifier.weight(1f))
                CategoryButton(text = "Custom", modifier = Modifier.weight(1f))
            }
        }
    }
}

@Composable
private fun CategoryButton(text: String, modifier: Modifier = Modifier) {
    Button(
        onClick = { /* TODO: handle click */ },
        modifier = modifier.height(80.dp)
    ) {
        Text(text, fontSize = 20.sp)
    }
}

@Preview(showBackground = true, device = "id:pixel_4")
@Composable
fun PreviewMyCategorizationScreen() {
    MyCategorizationScreen()
}
