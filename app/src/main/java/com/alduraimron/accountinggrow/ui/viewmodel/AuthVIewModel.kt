package com.alduraimron.accountinggrow.ui.screens.auth

import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.alduraimron.accountinggrow.data.repository.AuthRepository
import kotlinx.coroutines.launch

class AuthViewModel(
    private val repo: AuthRepository = AuthRepository()
) : ViewModel() {

    var authState by mutableStateOf<AuthState>(AuthState.Idle)
        private set

    fun login(email: String, password: String) {
        authState = AuthState.Loading

        viewModelScope.launch {
            try {
                repo.login(email, password)
                authState = AuthState.Success
            } catch (e: Exception) {
                authState = AuthState.Error(e.message ?: "Unknown error")
            }
        }
    }

    fun register(email: String, password: String, username: String) {
        authState = AuthState.Loading

        viewModelScope.launch {
            try {
                repo.register(email, password, username)
                authState = AuthState.Success
            } catch (e: Exception) {
                authState = AuthState.Error(e.message ?: "Unknown error")
            }
        }
    }

    fun logout() {
        repo.logout()
        authState = AuthState.Idle
    }

    fun resetState() {
        authState = AuthState.Idle
    }
}

sealed class AuthState {
    object Idle : AuthState()
    object Loading : AuthState()
    object Success : AuthState()
    data class Error(val message: String) : AuthState()
}