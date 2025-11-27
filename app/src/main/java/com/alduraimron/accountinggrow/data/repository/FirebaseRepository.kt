package com.alduraimron.accountinggrow.data.repository

import com.alduraimron.accountinggrow.data.model.SavingEntity
import com.alduraimron.accountinggrow.data.model.SavingType
import com.google.firebase.auth.FirebaseAuth
object FirebaseRepository {
    private val auth = FirebaseAuth.getInstance()

    private fun requireUid(): String {
        return auth.currentUser?.uid ?: throw IllegalStateException("No logged-in user")
    }

    fun getOngoingSavings(callback: (List<SavingEntity>) -> Unit) {
        val uid = requireUid()
        val placeholder = listOf(
            SavingEntity("1", uid, "Civic Turbo", 100_000_000.0, 10_000_000.0, SavingType.MONTHLY, false)
        )
        callback(placeholder)
    }

    fun getCompletedSavings(callback: (List<SavingEntity>) -> Unit) {
        val uid = requireUid()
        val placeholder = listOf(
            SavingEntity("2", uid, "idk", 100.0, 10.0, SavingType.WEEKLY, true)
        )
        callback(placeholder)
    }

    fun addSaving(saving: SavingEntity, callback: (Boolean) -> Unit) {
        val uid = requireUid()
        val savingWithUser = saving.copy(userId = uid)
        callback(true)
    }

    fun updateSaving(saving: SavingEntity, callback: (Boolean) -> Unit) {
        requireUid()
        callback(true)
    }

    fun deleteSaving(savingId: String, callback: (Boolean) -> Unit) {
        requireUid()
        callback(true)
    }
}
