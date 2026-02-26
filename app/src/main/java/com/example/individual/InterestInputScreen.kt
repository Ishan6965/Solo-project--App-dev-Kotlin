package com.example.individual

import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
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
    val scope = rememberCoroutineScope()

    var interestText by remember { mutableStateOf("") }
    var interests by remember { mutableStateOf(listOf<Pair<String, String>>()) } // Pair<interest, docId>

    // Load interests from Firestore when screen starts
    LaunchedEffect(category) {
        FirestoreHelper.getInterestsWithId(category) { list ->
            interests = list.map { Pair(it.first.toString(), it.second.toString()) }
        }
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
                            FirestoreHelper.addInterest(category, interestText) { docId ->
                                interests = interests + Pair(interestText, docId)
                                interestText = ""
                                Toast.makeText(context, "Saved!", Toast.LENGTH_SHORT).show()
                            }
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
            items(
                items = interests,
                key = { it.second } // unique Firestore docId
            ) { (interest, docId) ->
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "• $interest",
                        fontSize = 18.sp,
                        modifier = Modifier.weight(1f)
                    )

                    IconButton(
                        onClick = {
                            scope.launch {
                                try {
                                    FirestoreHelper.deleteInterest(category, docId) {
                                        interests = interests.filter { it.second != docId }
                                        Toast.makeText(context, "Deleted!", Toast.LENGTH_SHORT).show()
                                    }
                                } catch (e: Exception) {
                                    Toast.makeText(context, "Error: ${e.message}", Toast.LENGTH_LONG).show()
                                }
                            }
                        }
                    ) {
                        Icon(
                            imageVector = Icons.Filled.Delete,
                            contentDescription = "Delete Interest",
                            tint = MaterialTheme.colorScheme.error
                        )
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