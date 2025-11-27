package com.alduraimron.accountinggrow.data.repository

import com.alduraimron.accountinggrow.data.model.SavingEntity
import com.alduraimron.accountinggrow.data.model.SavingType
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

class FirebaseRepository(
    private val auth: FirebaseAuth = FirebaseAuth.getInstance(),
) {

    /**
     * Placeholder function to fetch savings for a user.
     * Returns a dummy SavingEntity for testing purposes.
     */
    private fun requireUid(): String {
        return auth.currentUser?.uid
            ?: throw IllegalStateException("No logged-in user")
    }
    fun getOngoingSavings(callback: (List<SavingEntity>) -> Unit) {
        // Create some dummy savings
        val placeholderSavings = listOf(
            SavingEntity(
                id = "1",
                userId = requireUid(),
                name = "Civic Turbo",
                target = 100000000.0,
                amount = 10000000.0,
                isCompleted = false,
                type = SavingType.MONTHLY
            ),
            SavingEntity(
                id = "4",
                userId = requireUid(),
                name = "Mobil Civic Turbo",
                target = 100000000.0,
                amount = 10000000.0,
                isCompleted = false,
                type = SavingType.MONTHLY
            ),
            SavingEntity(
                id = "5",
                userId = requireUid(),
                name = "Mobil Civic Turbo",
                target = 100000000.0,
                amount = 10000000.0,
                isCompleted = false,
                type = SavingType.MONTHLY
            )
        )

        // Simulate async behavior like a real Firebase call
        callback(placeholderSavings.filter { it.userId == requireUid() })
    }

    fun getCompletedSavings(callback: (List<SavingEntity>) -> Unit) {
        // Create some dummy savings
        val placeholderSavings = listOf(
            SavingEntity(
                id = "2",
                userId = requireUid(),
                name = "idk",
                target = 100.0,
                amount = 10.0,
                isCompleted = true,
                type = SavingType.WEEKLY
            )
        )

        // Simulate async behavior like a real Firebase call
        callback(placeholderSavings.filter { it.userId == requireUid() })
    }

        /**
     * Placeholder function to add a new saving.
     * Does nothing but calls callback with true.
     */
    fun addSaving(saving: SavingEntity, callback: (Boolean) -> Unit) {
        callback(true)
    }

    /**
     * Placeholder update function.
     */
    fun updateSaving(saving: SavingEntity, callback: (Boolean) -> Unit) {
        callback(true)
    }

    /**
     * Placeholder delete function.
     */
    fun deleteSaving(savingId: String, callback: (Boolean) -> Unit) {
        callback(true)
    }
}