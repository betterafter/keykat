package com.keykat.presentation.screen.career

import com.keykat.domain.career.entity.CareerEntity

sealed class CareerUiState {
    data object Init : CareerUiState()
    data class Success(val careerEntity: List<CareerEntity>) : CareerUiState()
    data class Error(val error: Throwable? = Throwable("data is null")) : CareerUiState()
    data object Loading : CareerUiState()
}