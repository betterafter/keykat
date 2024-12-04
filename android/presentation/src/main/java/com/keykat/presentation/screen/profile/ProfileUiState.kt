package com.keykat.presentation.screen.profile

import com.keykat.domain.profile.entity.EducationEntity
import com.keykat.domain.profile.entity.ProfileEntity
import com.keykat.domain.profile.entity.TechEntity


sealed class TopProfileUiState {
    data object Init : TopProfileUiState()
    data class Success(val profileEntity: ProfileEntity) : TopProfileUiState()
    data class Error(val error: Throwable? = Throwable("data is null")) : TopProfileUiState()
    data object Loading : TopProfileUiState()
}

sealed class BottomProfileUiState {
    data object Init : BottomProfileUiState()
    data class Success(val bottomProfileEntity: Pair<List<EducationEntity>, List<TechEntity>>) :
        BottomProfileUiState()

    data class Error(val error: Throwable? = Throwable("data is null")) : BottomProfileUiState()
    data object Loading : BottomProfileUiState()
}