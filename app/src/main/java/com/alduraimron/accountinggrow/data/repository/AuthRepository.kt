package com.alduraimron.accountinggrow.data.repository

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await

class AuthRepository(
    private val auth: FirebaseAuth = FirebaseAuth.getInstance(),
    private val db: FirebaseFirestore = FirebaseFirestore.getInstance()
) {

    suspend fun register(email: String, password: String, username: String) {
        val result = auth.createUserWithEmailAndPassword(email, password).await()
        val uid = result.user!!.uid

        val userData = mapOf("username" to username)
        db.collection("users").document(uid).set(userData).await()
    }

    suspend fun login(email: String, password: String) {
        auth.signInWithEmailAndPassword(email, password).await()
    }

    fun logout() {
        auth.signOut()
    }
}
