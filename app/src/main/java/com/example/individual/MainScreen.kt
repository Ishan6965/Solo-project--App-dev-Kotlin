package com.example.individual

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.material3.OutlinedTextField
import androidx.navigation.NavController


@Composable
fun MainScreen(navController: NavController)  {
    val searchQuery = remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFBB86FC))
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color(0xFF0D47A1))
                .padding(top = 45.dp, start = 16.dp, end = 16.dp),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            Button(
                onClick = {navController.navigate("categorization") },
                modifier = Modifier
                    .width(160.dp)
                    .height(80.dp)
            ) {
                Text("Categorize", fontSize = 22.sp)
            }

            Button(
                onClick = { },
                modifier = Modifier
                    .width(160.dp)
                    .height(80.dp)
            ) {
                Text("Settings & Feedback", fontSize = 22.sp)
            }
        }

        Spacer(modifier = Modifier.weight(1f))

        Box(
            modifier = Modifier.fillMaxWidth(),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = "Your Shopping List is empty",
                fontSize = 24.sp
            )
        }

        Spacer(modifier = Modifier.weight(1f))

        OutlinedTextField(
            value = searchQuery.value,
            onValueChange = { searchQuery.value = it },
            placeholder = { Text("Search items", fontSize = 24.sp) },
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 16.dp, end = 16.dp, bottom = 90.dp)
                .height(90.dp)
        )
    }
}
