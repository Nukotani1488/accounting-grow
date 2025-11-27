package com.alduraimron.accountinggrow.ui.viewmodel

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.alduraimron.accountinggrow.data.model.SavingEntity
import com.alduraimron.accountinggrow.data.repository.FirebaseRepository

class LazyLoadingState<T>(
    private val loader: () -> Unit,
    private val backing: State<T>
) : State<T> {

    private var loaded = false

    override val value: T
        get() {
            if (!loaded) {
                loaded = true
                loader()
            }
            return backing.value
        }
}


class SavingViewModel(val userId: String) : ViewModel() {

    private val repository = FirebaseRepository(userId)

    private val _ongoingSavings = mutableStateOf<List<SavingEntity>>(emptyList())
    private val _completedSavings = mutableStateOf<List<SavingEntity>>(emptyList())

    val ongoingSavings: State<List<SavingEntity>> =
        LazyLoadingState(
            loader = { loadOngoingSavings() },
            backing = _ongoingSavings
        )

    val completedSavings: State<List<SavingEntity>> =
        LazyLoadingState(
            loader = { loadCompletedSavings() },
            backing = _completedSavings
        )

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
