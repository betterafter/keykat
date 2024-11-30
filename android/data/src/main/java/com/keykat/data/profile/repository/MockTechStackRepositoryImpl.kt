package com.keykat.data.profile.repository

import com.keykat.data.profile.datasource.MockProfileApi
import com.keykat.domain.profile.entity.TechEntity
import com.keykat.domain.profile.repository.TechStackRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class MockTechStackRepositoryImpl(
    private val api: MockProfileApi
) : TechStackRepository {
    override suspend fun getTechStack(): Flow<TechEntity> {
        return flow {
            api.getTechStack()
        }
    }
}