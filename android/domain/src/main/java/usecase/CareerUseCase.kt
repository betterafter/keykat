package usecase

import com.keykat.domain.career.entity.CareerEntity
import com.keykat.domain.career.repository.CareerRepository
import kotlinx.coroutines.flow.Flow

class CareerUseCase(
    private val careerRepository: CareerRepository
) {
    suspend fun getCareer() : Flow<List<CareerEntity>> {
        return careerRepository.getCareer()
    }
}