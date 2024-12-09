package com.keykat.data.potfolio.repository

import com.keykat.domain.potfolio.entity.PortfolioEntity
import com.keykat.domain.potfolio.repository.PortfolioRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class PortfolioRepositoryImpl @Inject constructor(

) : PortfolioRepository {
    override suspend fun getPortfolio(): Flow<PortfolioEntity> {
        TODO("Not yet implemented")
    }
}