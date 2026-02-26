package com.example.individual

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await

object FirestoreHelper {

    private val db = FirebaseFirestore.getInstance()
    private val auth = FirebaseAuth.getInstance()

    fun getUserId(): String? = auth.currentUser?.uid

    fun getCategoryCollection(category: String) =
        getUserId()?.let { uid -> db.collection("users").document(uid).collection(category) }

    // Add interest
    suspend fun addInterest(category: String, interest: String) {
        getCategoryCollection(category)?.add(mapOf("interest" to interest))?.await()
    }

    // Get interests with their document IDs
    suspend fun getInterestsWithId(category: String): List<Pair<String, String>> {
        val docs = getCategoryCollection(category)?.get()?.await()
        return docs?.documents?.mapNotNull { doc ->
            val text = doc.getString("interest")
            val id = doc.id
            if (text != null) text to id else null
        } ?: emptyList()
    }

    // Delete interest by document ID
    suspend fun deleteInterest(category: String, docId: String) {
        getCategoryCollection(category)?.document(docId)?.delete()?.await()
    }
}