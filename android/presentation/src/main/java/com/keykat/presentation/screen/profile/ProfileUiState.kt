package com.keykat.presentation.screen.profile

sealed class ProfileUiState {
    data object Init: ProfileUiState()
    data class Success<T>(val data: T): ProfileUiState()
    data class Error(val error: Throwable?): ProfileUiState()
    data object Loading: ProfileUiState()
}