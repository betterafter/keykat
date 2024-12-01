package com.keykat.data.profile.repository

import com.keykat.data.profile.datasource.MockProfileApi
import com.keykat.domain.profile.entity.EducationEntity
import com.keykat.domain.profile.repository.EducationRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class MockEducationRepositoryImpl @Inject constructor(
    private val api: MockProfileApi
) : EducationRepository {
    override suspend fun getEducationList(): Flow<List<EducationEntity>> {
        return flow {
            api.getMockEducation()
        }
    }
}