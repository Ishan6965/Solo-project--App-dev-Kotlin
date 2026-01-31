package com.example.individual

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.example.individual.ui.theme.IndividualTheme
import com.google.firebase.FirebaseApp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Initialize Firebase
        FirebaseApp.initializeApp(this)

        // Enable edge-to-edge content
        enableEdgeToEdge()

        // Set Compose content
        setContent {
            IndividualTheme {
                AppNavigation()
            }
        }
    }
}
