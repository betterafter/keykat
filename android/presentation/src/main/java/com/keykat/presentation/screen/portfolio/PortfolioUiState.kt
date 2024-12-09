package com.keykat.presentation.screen.portfolio

import com.keykat.domain.potfolio.entity.PortfolioEntity

sealed class PortfolioUiState {
    data object Init : PortfolioUiState()
    data class Success(val portfolioEntity: PortfolioEntity) : PortfolioUiState()
    data class Error(val error: Throwable? = Throwable("data is null")) : PortfolioUiState()
    data object Loading : PortfolioUiState()
}