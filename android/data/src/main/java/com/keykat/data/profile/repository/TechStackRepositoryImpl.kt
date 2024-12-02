package com.keykat.data.profile.repository

import com.keykat.data.profile.datasource.ProfileApi
import com.keykat.data.profile.dto.ProfileMapper.toDomain
import com.keykat.domain.profile.entity.TechEntity
import com.keykat.domain.profile.repository.TechStackRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class TechStackRepositoryImpl @Inject constructor(
    private val profileApi: ProfileApi
) : TechStackRepository {
    override suspend fun getTechStack(): Flow<List<TechEntity>> {
        return flow {
            try {
                val educationEntity = profileApi.getTech().body()?.mapNotNull {
                    it.toDomain()
                } ?: mutableListOf()

                emit(educationEntity)
            } catch (e: Exception) {
                emit(mutableListOf())
            }
        }
    }
}