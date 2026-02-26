package com.example.individual

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@Composable
fun MyCategorizationScreen(navController: NavController) {
    Column(
        modifier = Modifier.fillMaxSize()
    ) {

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(130.dp),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = "Choose your categorization",
            )
        }

        Column(
            modifier = Modifier
                .fillMaxSize()
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
                    onClick = { navController.navigate("interest/short_term") },
                    modifier = Modifier.weight(1f)
                )
                CategoryButton(
                    text = "Long term Goals",
                    onClick = { navController.navigate("interest/long_term") },
                    modifier = Modifier.weight(1f)
                )
            }

            Spacer(modifier = Modifier.height(50.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                CategoryButton(
                    text = "Wishlist",
                    onClick = { navController.navigate("interest/wishlist") },
                    modifier = Modifier.weight(1f)
                )
                CategoryButton(
                    text = "Shopping List",
                    onClick = { /* optional future screen */ },
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
        Text(text)
    }
}