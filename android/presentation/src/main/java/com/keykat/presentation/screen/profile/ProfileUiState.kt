package com.keykat.presentation.screen.profile

import com.keykat.domain.profile.entity.ProfileEntity


sealed class ProfileUiState {
    data object Init: ProfileUiState()
    data class Success(val profileEntity: ProfileEntity): ProfileUiState()
    data class Error(val error: Throwable?): ProfileUiState()
    data object Loading: ProfileUiState()
}