package com.keykat.data.profile.repository

import com.keykat.data.profile.datasource.MockProfileApi
import com.keykat.data.profile.dto.ProfileMapper.toDomain
import com.keykat.domain.profile.entity.TechEntity
import com.keykat.domain.profile.repository.TechStackRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class MockTechStackRepositoryImpl @Inject constructor(
    private val api: MockProfileApi
) : TechStackRepository {
    override suspend fun getTechStack(): Flow<List<TechEntity>> {
        try {
            return flow {
                val entityList = api.getTechStack()?.mapNotNull {
                    it.toDomain()
                }

                if (entityList != null) {
                    emit(entityList)
                }
            }
        } catch (e: Exception) {
            throw e
        }
    }
}