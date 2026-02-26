package com.example.individual

import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await

object FirestoreHelper {
    private val db = FirebaseFirestore.getInstance()

    // Add a new interest and return the docId
    suspend fun addInterest(category: String, interest: String): String {
        val docRef = db.collection(category).document()
        docRef.set(mapOf("interest" to interest)).await()
        return docRef.id
    }

    // Get all interests with docId
    suspend fun getInterestsWithId(category: String): List<Pair<String, String>> {
        val snapshot = db.collection(category).get().await()
        return snapshot.documents.map { doc ->
            val interest = doc.get("interest").toString()
            val docId = doc.id
            Pair(interest, docId)
        }
    }

    // Delete an interest by docId
    suspend fun deleteInterest(category: String, docId: String) {
        db.collection(category).document(docId).delete().await()
    }
}