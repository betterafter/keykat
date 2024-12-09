package com.keykat.data.potfolio.repository

import com.keykat.data.potfolio.datasource.PortfolioApi
import com.keykat.data.potfolio.dto.PortfolioMapper.toDomain
import com.keykat.data.profile.dto.ProfileMapper.toDomain
import com.keykat.domain.potfolio.entity.PortfolioEntity
import com.keykat.domain.potfolio.repository.PortfolioRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class PortfolioRepositoryImpl @Inject constructor(
    private val portfolioApi: PortfolioApi
) : PortfolioRepository {
    override suspend fun getPortfolio(): Flow<PortfolioEntity?> {
        return flow {
            try {
                val portfolioDto = portfolioApi.getPortfolio().body()?.toDomain()
                println("[keykat] portfolioDto: $portfolioDto")
                emit(portfolioDto)
            } catch (e: Exception) {
                println("[keykat] e: $e")
                emit(null)
            }
        }
    }
}