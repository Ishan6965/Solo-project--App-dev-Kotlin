package com.example.individual

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.individual.R
import com.google.firebase.auth.FirebaseAuth

@Composable
fun LoginScreen(navController: NavController) {

    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    val context = LocalContext.current
    val auth = FirebaseAuth.getInstance() // Firebase Auth instance

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {

        Image(
            painter = painterResource(id = R.drawable.pic2),
            contentDescription = "Login Image",
            modifier = Modifier
                .size(120.dp)
                .padding(top = 20.dp, bottom = 20.dp)
        )

        OutlinedTextField(
            value = email,
            onValueChange = { email = it },
            label = { Text("Email") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = password,
            onValueChange = { password = it },
            label = { Text("Password") },
            visualTransformation = PasswordVisualTransformation(),
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = {
                if (email.isEmpty() || password.isEmpty()) {
                    Toast.makeText(context, "Please enter email and password", Toast.LENGTH_SHORT).show()
                    return@Button
                }

                // Firebase login
                auth.signInWithEmailAndPassword(email, password)
                    .addOnCompleteListener { task ->
                        if (task.isSuccessful) {
                            Toast.makeText(context, "Login Successful", Toast.LENGTH_SHORT).show()
                            navController.navigate("main") {
                                popUpTo("login") { inclusive = true } // removes login from back stack
                            }
                        } else {
                            Toast.makeText(
                                context,
                                "Login Failed: ${task.exception?.message}",
                                Toast.LENGTH_LONG
                            ).show()
                        }
                    }
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(text = "Login", fontSize = 20.sp)
        }

        Spacer(modifier = Modifier.height(12.dp))

        Text(
            text = "Forgot Password?",
            color = Color.Red,
            modifier = Modifier
                .align(Alignment.End)
                .padding(end = 8.dp)
        )

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = { navController.navigate("signup") },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(text = "Register", fontSize = 20.sp)
        }
    }
}
