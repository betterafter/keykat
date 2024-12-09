package com.keykat.presentation.screen.portfolio

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.navigation.NavController

@Composable
fun PortfolioScreen(
    navController: NavController,
    portfolioViewModel: PortfolioViewModel,
) {
    val portFolioUiState = portfolioViewModel.portfolioUiState.collectAsState()
    LaunchedEffect(Unit) {
        portfolioViewModel.getPortfolio()
    }

    when (portFolioUiState.value) {
        is PortfolioUiState.Success -> {
            val portfolioCareerEntity =
                (portFolioUiState.value as PortfolioUiState.Success)
                    .portfolioEntity
                    .portfolioCareerEntity
            val portfolioProjectEntity =
                (portFolioUiState.value as PortfolioUiState.Success)
                    .portfolioEntity
                    .portfolioProjectEntity
            Column {
                if (portfolioCareerEntity != null) {
                    PortfolioCareerWidget(
                        navController = navController,
                        portfolioCareerEntity = portfolioCareerEntity
                    )
                }
                if (portfolioProjectEntity != null) {
                    PortfolioProjectWidget(
                        portfolioProjectEntity = portfolioProjectEntity
                    )
                }
            }
        }

        is PortfolioUiState.Init -> {}
        is PortfolioUiState.Loading -> {}
        is PortfolioUiState.Error -> {}
    }
}