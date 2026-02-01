package com.example.individual

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
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController

@Composable
fun MyCategorizationScreen(navController: NavController) {
    Column(
        modifier = Modifier.fillMaxSize()
    ) {

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

        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color(0xFFFFEB3B))
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                CategoryButton(
                    text = "Short term Goals",
                    onClick = { navController.navigate("short_term") },
                    modifier = Modifier.weight(1f)
                )
                CategoryButton(
                    text = "Long term Goals",
                    onClick = { navController.navigate("long_term") },
                    modifier = Modifier.weight(1f)
                )
            }

            Spacer(modifier = Modifier.height(75.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                CategoryButton(
                    text = "Wishlist",
                    onClick = { navController.navigate("wishlist") },
                    modifier = Modifier.weight(1f)
                )
                CategoryButton(
                    text = "Shopping List",
                    onClick = { /* TODO: navigate("shopping_list") */ },
                    modifier = Modifier.weight(1f)
                )
            }

            Spacer(modifier = Modifier.height(70.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                CategoryButton(
                    text = "Notes",
                    onClick = { /* TODO: navigate("notes") */ },
                    modifier = Modifier.weight(1f)
                )
                CategoryButton(
                    text = "Custom",
                    onClick = { /* TODO: navigate("custom") */ },
                    modifier = Modifier.weight(1f)
                )
            }
        }
    }
}

@Composable
private fun RowScope.CategoryButton(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Button(
        onClick = onClick,
        modifier = modifier.height(80.dp)
    ) {
        Text(text, fontSize = 20.sp)
    }
}

@Preview(showBackground = true, device = "id:pixel_4")
@Composable
fun PreviewMyCategorizationScreen() {
    MyCategorizationScreen(navController = rememberNavController())
}
