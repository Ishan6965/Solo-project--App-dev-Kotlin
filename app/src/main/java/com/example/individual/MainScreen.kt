package com.example.individual

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.*
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.launch

@Composable
fun MainScreen(navController: NavController) {
    val context = LocalContext.current
    val db = FirebaseFirestore.getInstance()

    var newItem by remember { mutableStateOf("") }
    var shoppingList by remember { mutableStateOf(listOf<Pair<String, String>>()) } // Pair<text, docId>
    val scope = rememberCoroutineScope()

    // Load shopping list from Firestore
    LaunchedEffect(Unit) {
        db.collection("shopping_list")
            .get()
            .addOnSuccessListener { result ->
                shoppingList = result.map { doc ->
                    Pair(doc.getString("text") ?: "", doc.id)
                }
            }
            .addOnFailureListener {
                Toast.makeText(context, "Failed to load shopping list", Toast.LENGTH_SHORT).show()
            }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFBB86FC))
            .padding(bottom = 16.dp)
    ) {
        // Top Buttons
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color(0xFF0D47A1))
                .padding(top = 45.dp, start = 16.dp, end = 16.dp),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            Button(
                onClick = { navController.navigate("categorization") },
                modifier = Modifier
                    .width(160.dp)
                    .height(80.dp)
            ) {
                Text("Categorize", fontSize = 22.sp)
            }

            Button(
                onClick = { navController.navigate("settings_feedback") },
                modifier = Modifier
                    .width(160.dp)
                    .height(80.dp)
            ) {
                Text("Settings & Feedback", fontSize = 22.sp)
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Shopping List Display
        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
                .padding(horizontal = 16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            if (shoppingList.isEmpty()) {
                item {
                    Text(
                        "Your Shopping List is empty",
                        fontSize = 24.sp,
                        modifier = Modifier.fillMaxWidth(),
                        color = Color.White
                    )
                }
            }

            itemsIndexed(shoppingList, key = { index, item -> item.second }) { index, (text, docId) ->
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(Color(0xFFE1BEE7))
                        .padding(12.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = "• $text",
                        fontSize = 20.sp,
                        modifier = Modifier.weight(1f)
                    )

                    IconButton(onClick = {
                        // Delete item
                        scope.launch {
                            db.collection("shopping_list")
                                .document(docId)
                                .delete()
                                .addOnSuccessListener {
                                    shoppingList = shoppingList.toMutableList().also { it.removeAt(index) }
                                    Toast.makeText(context, "Deleted!", Toast.LENGTH_SHORT).show()
                                }
                                .addOnFailureListener {
                                    Toast.makeText(context, "Failed to delete", Toast.LENGTH_SHORT).show()
                                }
                        }
                    }) {
                        Icon(
                            imageVector = Icons.Filled.Delete,
                            contentDescription = "Delete",
                            tint = MaterialTheme.colorScheme.error
                        )
                    }
                }
            }
        }

        Spacer(modifier = Modifier.height(8.dp))

        // Input bar with Save (+) icon
        OutlinedTextField(
            value = newItem,
            onValueChange = { newItem = it },
            placeholder = { Text("Search items", fontSize = 20.sp) },
            trailingIcon = {
                IconButton(onClick = {
                    if (newItem.isNotBlank()) {
                        // Add item to Firestore
                        scope.launch {
                            db.collection("shopping_list")
                                .add(hashMapOf("text" to newItem))
                                .addOnSuccessListener { docRef ->
                                    shoppingList = shoppingList + Pair(newItem, docRef.id)
                                    newItem = ""
                                    Toast.makeText(context, "Saved!", Toast.LENGTH_SHORT).show()
                                }
                                .addOnFailureListener {
                                    Toast.makeText(context, "Failed to save", Toast.LENGTH_SHORT).show()
                                }
                        }
                    } else {
                        Toast.makeText(context, "Enter something", Toast.LENGTH_SHORT).show()
                    }
                }) {
                    Icon(
                        imageVector = Icons.Filled.Add,
                        contentDescription = "Add Item",
                        tint = Color.White
                    )
                }
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 8.dp)
        )
    }
}