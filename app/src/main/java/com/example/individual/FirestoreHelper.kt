package com.example.individual

import com.google.firebase.firestore.FirebaseFirestore

object FirestoreHelper {

    private val db = FirebaseFirestore.getInstance()

    // Fetch all interests for a category
    fun getInterestsWithId(
        category: String,
        callback: (List<Pair<String, String>>) -> Unit
    ) {
        db.collection(category)
            .get()
            .addOnSuccessListener { result ->
                val list = result.map { doc ->
                    Pair(doc.getString("text") ?: "", doc.id)
                }
                callback(list)
            }
            .addOnFailureListener {
                callback(emptyList())
            }
    }

    // Add interest and return the docId via callback
    fun addInterest(
        category: String,
        text: String,
        callback: (String) -> Unit
    ) {
        val data = hashMapOf("text" to text)
        db.collection(category)
            .add(data)
            .addOnSuccessListener { docRef ->
                callback(docRef.id)
            }
            .addOnFailureListener {
                callback("")
            }
    }

    // Delete interest by docId
    fun deleteInterest(
        category: String,
        docId: String,
        callback: () -> Unit
    ) {
        db.collection(category)
            .document(docId)
            .delete()
            .addOnSuccessListener {
                callback()
            }
            .addOnFailureListener {
                callback()
            }
    }
}