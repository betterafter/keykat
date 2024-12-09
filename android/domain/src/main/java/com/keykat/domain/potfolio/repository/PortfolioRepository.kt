package com.keykat.domain.potfolio.repository

import com.keykat.domain.potfolio.entity.PortfolioEntity
import kotlinx.coroutines.flow.Flow

interface PortfolioRepository {
    suspend fun getPortfolio(): Flow<PortfolioEntity?>
}