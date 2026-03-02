package com.example.individual

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import kotlinx.coroutines.launch
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete

@Composable
fun ShoppingListScreen(navController: NavController) {
    val scope = rememberCoroutineScope()
    var text by remember { mutableStateOf("") }
    var items by remember { mutableStateOf(listOf<Pair<String, String>>()) }
    val collection = "shopping_list"

    // Load items when screen opens
    LaunchedEffect(Unit) {
        try {
            items = FirebaseHelper.getItems(collection)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {

        Text("Your Shopping List", fontSize = 24.sp)
        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = text,
            onValueChange = { text = it },
            label = { Text("Add an item") },
            modifier = Modifier.fillMaxWidth(),
            trailingIcon = {
                IconButton(onClick = {
                    if (text.isNotBlank()) {
                        scope.launch {
                            try {
                                val id = FirebaseHelper.addItem(collection, text)
                                items = items + (text to id)
                                text = ""
                            } catch (e: Exception) {
                                e.printStackTrace()
                            }
                        }
                    }
                }) {
                    Icon(Icons.Default.Add, contentDescription = "Add")
                }
            }
        )

        Spacer(modifier = Modifier.height(20.dp))

        LazyColumn(modifier = Modifier.fillMaxWidth()) {
            itemsIndexed(items, key = { index, item -> "${item.second}_$index" }) { index, (itemText, docId) ->
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text("• $itemText", fontSize = 20.sp)
                    IconButton(onClick = {
                        scope.launch {
                            try {
                                FirebaseHelper.deleteItem(collection, docId)
                                items = items.toMutableList().also { it.removeAt(index) }
                            } catch (e: Exception) {
                                e.printStackTrace()
                            }
                        }
                    }) {
                        Icon(Icons.Default.Delete, contentDescription = "Delete")
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