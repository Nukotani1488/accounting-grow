package com.alduraimron.accountinggrow.data.repository

import com.alduraimron.accountinggrow.data.model.SavingEntity

class SavingRepository(val userId: String) {

    /**
     * Placeholder function to fetch savings for a user.
     * Returns a dummy SavingEntity for testing purposes.
     */
    fun getSavings(callback: (List<SavingEntity>) -> Unit) {
        // Create some dummy savings
        val placeholderSavings = listOf(
            SavingEntity(
                id = "1",
                userId = userId,
                name = "Civic Turbo",
                target = 100000000.0,
                amount = 10000000.0,
                isCompleted = false
            ),
            SavingEntity(
                id = "2",
                userId = userId,
                name = "idk",
                target = 100.0,
                amount = 10.0,
                isCompleted = true
            )
        )

        // Simulate async behavior like a real Firebase call
        callback(placeholderSavings.filter { it.userId == userId })
    }

    fun getOngoingSavings(callback: (List<SavingEntity>) -> Unit) {
        // Create some dummy savings
        val placeholderSavings = listOf(
            SavingEntity(
                id = "1",
                userId = userId,
                name = "Civic Turbo",
                target = 100000000.0,
                amount = 10000000.0,
                isCompleted = false
            )
        )

        // Simulate async behavior like a real Firebase call
        callback(placeholderSavings.filter { it.userId == userId })
    }

    fun getCompletedSavings(callback: (List<SavingEntity>) -> Unit) {
        // Create some dummy savings
        val placeholderSavings = listOf(
            SavingEntity(
                id = "2",
                userId = userId,
                name = "idk",
                target = 100.0,
                amount = 10.0,
                isCompleted = true
            )
        )

        // Simulate async behavior like a real Firebase call
        callback(placeholderSavings.filter { it.userId == userId })
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