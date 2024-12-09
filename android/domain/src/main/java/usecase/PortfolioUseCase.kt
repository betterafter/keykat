package usecase

import com.keykat.domain.potfolio.entity.PortfolioEntity
import com.keykat.domain.potfolio.repository.PortfolioRepository
import kotlinx.coroutines.flow.Flow

class PortfolioUseCase(
    private val portfolioRepository: PortfolioRepository
) {
    suspend fun getPortfolio(): Flow<PortfolioEntity?> {
        return portfolioRepository.getPortfolio()
    }
}