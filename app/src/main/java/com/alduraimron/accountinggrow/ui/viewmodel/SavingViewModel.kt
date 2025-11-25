package com.alduraimron.accountinggrow.ui.viewmodel

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.alduraimron.accountinggrow.data.model.SavingEntity
import com.alduraimron.accountinggrow.data.repository.SavingRepository

class SavingViewModel(val userId: String) : ViewModel() {

    private val repository = SavingRepository(userId)

    val ongoingSavings = mutableStateOf<List<SavingEntity>>(emptyList())
    val completedSavings = mutableStateOf<List<SavingEntity>>(emptyList())

    fun loadOngoingSavings() {
        repository.getOngoingSavings { fetched ->
            ongoingSavings.value = fetched
        }
    }

    fun loadCompletedSavings() {
        repository.getCompletedSavings { fetched ->
            completedSavings.value = fetched
        }
    }

    fun addSaving(saving: SavingEntity) {
        repository.addSaving(saving) { success ->
            if (success) {
                // reload only the relevant status
                if (saving.amount < saving.target) loadOngoingSavings()
                else loadCompletedSavings()
            }
        }
    }

    fun updateSaving(saving: SavingEntity) {
        repository.updateSaving(saving) { success ->
            if (success) {
                loadOngoingSavings()
                loadCompletedSavings()
            }
        }
    }

    fun deleteSaving(savingId: String, wasOngoing: Boolean) {
        repository.deleteSaving(savingId) { success ->
            if (success) {
                if (wasOngoing) loadOngoingSavings()
                else loadCompletedSavings()
            }
        }
    }
}
