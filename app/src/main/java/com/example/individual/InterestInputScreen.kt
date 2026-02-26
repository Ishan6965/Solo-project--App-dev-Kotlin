package com.example.individual

import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import kotlinx.coroutines.launch

@Composable
fun InterestInputScreen(navController: NavController, category: String) {
    val context = LocalContext.current
    var interestText by remember { mutableStateOf("") }
    var interests by remember { mutableStateOf(listOf<Pair<String, String>>()) } // Pair<interest, docId>
    val scope = rememberCoroutineScope()

    // Load interests
    LaunchedEffect(category) {
        interests = FirestoreHelper.getInterestsWithId(category)
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Text("Write your interests for $category", fontSize = 24.sp)
        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = interestText,
            onValueChange = { interestText = it },
            label = { Text("Your interest") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = {
                if (interestText.isNotBlank()) {
                    scope.launch {
                        try {
                            FirestoreHelper.addInterest(category, interestText)
                            interests = FirestoreHelper.getInterestsWithId(category)
                            interestText = ""
                            Toast.makeText(context, "Saved!", Toast.LENGTH_SHORT).show()
                        } catch (e: Exception) {
                            Toast.makeText(context, "Error: ${e.message}", Toast.LENGTH_LONG).show()
                        }
                    }
                } else {
                    Toast.makeText(context, "Enter something", Toast.LENGTH_SHORT).show()
                }
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Save")
        }

        Spacer(modifier = Modifier.height(20.dp))

        Text("Your Interests:", fontSize = 20.sp)
        Spacer(modifier = Modifier.height(8.dp))

        LazyColumn(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(interests) { (interest, docId) ->
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text("• $interest", fontSize = 18.sp)

                    Button(
                        onClick = {
                            scope.launch {
                                try {
                                    FirestoreHelper.deleteInterest(category, docId)
                                    interests = FirestoreHelper.getInterestsWithId(category)
                                    Toast.makeText(context, "Deleted!", Toast.LENGTH_SHORT).show()
                                } catch (e: Exception) {
                                    Toast.makeText(context, "Error: ${e.message}", Toast.LENGTH_LONG).show()
                                }
                            }
                        },
                        modifier = Modifier.height(36.dp)
                    ) {
                        Text("Delete")
                    }
                }
            }
        }

        Spacer(modifier = Modifier.height(20.dp))

        Button(onClick = { navController.popBackStack() }) {
            Text("Back")
        }
    }
}