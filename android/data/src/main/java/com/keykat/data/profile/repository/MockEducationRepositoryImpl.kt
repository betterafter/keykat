package com.keykat.data.profile.repository

import com.keykat.domain.profile.entity.EducationEntity
import com.keykat.domain.profile.repository.EducationRepository
import kotlinx.coroutines.flow.Flow

class MockEducationRepositoryImpl : EducationRepository {
    override suspend fun getEducationList(): Flow<List<EducationEntity>> {
        TODO("Not yet implemented")
    }

}