package com.keykat.data.profile.repository

import com.keykat.data.profile.datasource.ProfileApi
import com.keykat.data.profile.dto.ProfileMapper.toDomain
import com.keykat.domain.profile.entity.EducationEntity
import com.keykat.domain.profile.repository.EducationRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class EducationRepositoryImpl @Inject constructor(
    private val profileApi: ProfileApi
) : EducationRepository {
    override suspend fun getEducationList(): Flow<List<EducationEntity>> {
        return flow {
            try {
                val educationEntity = profileApi.getEducation().body()?.mapNotNull {
                    it.toDomain()
                } ?: mutableListOf()

                emit(educationEntity)
            } catch (e: Exception) {
                emit(mutableListOf())
            }
        }
    }
}