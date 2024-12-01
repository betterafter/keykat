package usecase

import com.keykat.domain.profile.entity.EducationEntity
import com.keykat.domain.profile.entity.ProfileEntity
import com.keykat.domain.profile.entity.TechEntity
import com.keykat.domain.profile.repository.EducationRepository
import com.keykat.domain.profile.repository.ProfileRepository
import com.keykat.domain.profile.repository.TechStackRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.combine
import javax.inject.Inject

class ProfileUseCase(
    private val profileRepository: ProfileRepository,
    private val educationRepository: EducationRepository,
    private val techStackRepository: TechStackRepository
) {
    suspend fun getTopProfile(): Flow<ProfileEntity> {
        return profileRepository.getProfile()
    }

    suspend fun getBottomProfile(): Flow<Pair<List<EducationEntity>, List<TechEntity>>> {
        return educationRepository.getEducationList().combine(
            flow = techStackRepository.getTechStack(),
            transform = { f1, f2 ->
                Pair(f1, f2)
            }
        )
    }
}