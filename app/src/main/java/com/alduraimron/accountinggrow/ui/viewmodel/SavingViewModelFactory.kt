package com.alduraimron.accountinggrow.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class SavingViewModelFactory(private val userId: String) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(SavingViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return SavingViewModel(userId) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
