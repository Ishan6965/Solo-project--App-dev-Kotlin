package com.example.individual

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await

object FirebaseHelper {

    private val db = FirebaseFirestore.getInstance()
    private val auth = FirebaseAuth.getInstance()

    // Ensure user is signed in anonymously
    suspend fun ensureUser(): String {
        if (auth.currentUser == null) {
            val result = auth.signInAnonymously().await()
            return result.user?.uid ?: throw Exception("Failed to sign in")
        }
        return auth.currentUser!!.uid
    }

    // Add item to a collection
    suspend fun addItem(collection: String, text: String): String {
        ensureUser()
        val docRef = db.collection(collection).add(
            mapOf(
                "text" to text,
                "timestamp" to System.currentTimeMillis()
            )
        ).await()
        return docRef.id
    }

    // Delete item from collection
    suspend fun deleteItem(collection: String, docId: String) {
        ensureUser()
        db.collection(collection).document(docId).delete().await()
    }

    // Get all items from collection
    suspend fun getItems(collection: String): List<Pair<String, String>> {
        ensureUser()
        val snapshot = db.collection(collection)
            .orderBy("timestamp")
            .get()
            .await()
        return snapshot.documents.map { doc ->
            val text = doc.getString("text") ?: ""
            text to doc.id
        }
    }
}