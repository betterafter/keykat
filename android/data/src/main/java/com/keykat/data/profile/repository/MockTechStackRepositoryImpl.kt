package com.keykat.data.profile.repository

import com.keykat.domain.profile.entity.TechEntity
import com.keykat.domain.profile.repository.TechStackRepository
import kotlinx.coroutines.flow.Flow

class MockTechStackRepositoryImpl : TechStackRepository {
    override suspend fun getTechStack(): Flow<TechEntity> {
        TODO("Not yet implemented")
    }

}