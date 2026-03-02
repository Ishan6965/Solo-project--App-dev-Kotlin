package com.example.individual

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.google.firebase.firestore.FirebaseFirestore

@Composable
fun FeedbackScreen(navController: NavController) {

    val context = LocalContext.current
    val db = FirebaseFirestore.getInstance()

    var feedbackText by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFE3F2FD))
            .padding(20.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Spacer(modifier = Modifier.height(40.dp))

        Text(
            text = "Give feedback so we can improve",
            fontSize = 22.sp
        )

        Spacer(modifier = Modifier.height(40.dp))

        OutlinedTextField(
            value = feedbackText,
            onValueChange = { feedbackText = it },
            placeholder = { Text("Write your feedback") },
            modifier = Modifier
                .fillMaxWidth()
                .height(150.dp)
        )

        Spacer(modifier = Modifier.height(30.dp))

        Button(
            onClick = {
                if (feedbackText.isNotBlank()) {

                    val data = hashMapOf(
                        "feedback" to feedbackText,
                        "timestamp" to System.currentTimeMillis()
                    )

                    db.collection("feedbacks")
                        .add(data)
                        .addOnSuccessListener {
                            Toast.makeText(context, "Feedback submitted!", Toast.LENGTH_SHORT).show()
                            feedbackText = ""
                        }
                        .addOnFailureListener {
                            Toast.makeText(context, "Failed to submit", Toast.LENGTH_SHORT).show()
                        }

                } else {
                    Toast.makeText(context, "Write something first", Toast.LENGTH_SHORT).show()
                }
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Submit Feedback")
        }

        Spacer(modifier = Modifier.height(20.dp))

        Button(onClick = { navController.popBackStack() }) {
            Text("Back")
        }
    }
}