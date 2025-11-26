package com.alduraimron.accountinggrow.ui.viewmodel

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.alduraimron.accountinggrow.data.model.SavingEntity
import com.alduraimron.accountinggrow.data.repository.SavingRepository

class SavingViewModel(val userId: String) : ViewModel() {

    private val repository = SavingRepository(userId)

    private val _ongoingSavings = mutableStateOf<List<SavingEntity>>(emptyList())
    private var ongoingLoaded = false

    private val _completedSavings = mutableStateOf<List<SavingEntity>>(emptyList())
    private var completedLoaded = false

    val ongoingSavings
        get() = {
            if(ongoingLoaded) {
                _ongoingSavings
            } else {
                loadOngoingSavings()
                ongoingLoaded = true
            }
        }

    val completedSavings
        get() = {
            if(completedLoaded) {
                _completedSavings
            } else {
                loadCompletedSavings()
                completedLoaded = true
            }
        }

    private fun loadOngoingSavings() {
        repository.getOngoingSavings { fetched ->
            _ongoingSavings.value = fetched
        }
    }

    private fun loadCompletedSavings() {
        repository.getCompletedSavings { fetched ->
            _completedSavings.value = fetched
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
