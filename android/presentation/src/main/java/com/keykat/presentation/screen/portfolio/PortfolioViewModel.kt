package com.keykat.presentation.screen.portfolio

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import usecase.PortfolioUseCase
import javax.inject.Inject

@HiltViewModel
class PortfolioViewModel @Inject constructor(
    private val portfolioUseCase: PortfolioUseCase
) : ViewModel() {
    private val _portfolioUiState = MutableStateFlow<PortfolioUiState>(PortfolioUiState.Init)
    val portfolioUiState: MutableStateFlow<PortfolioUiState> = _portfolioUiState

    suspend fun getPortfolio() {
        portfolioUiState.value = PortfolioUiState.Loading
        viewModelScope.launch {
            portfolioUseCase.getPortfolio()
                .catch { e ->
                    portfolioUiState.value = PortfolioUiState.Error(e)
                }
                .collect {
                    if (it == null) {
                        portfolioUiState.value = PortfolioUiState.Error()
                    } else {
                        val portfolio = it.copy()

                        it.portfolioCareerEntity?.forEach { curr ->
                            portfolio.portfolioCareerEntity?.mapIndexed { index, po ->
                                if (po.name == curr.parent) {
                                    portfolio.portfolioCareerEntity!![index].children.add(curr)
                                }
                            }
                        }

                        portfolioUiState.value = PortfolioUiState.Success(portfolio)
                    }
                }
        }
    }
}