package com.keykat.data.profile.repository

import com.keykat.data.profile.datasource.MockProfileApi
import com.keykat.domain.profile.entity.ProfileEntity
import com.keykat.domain.profile.repository.ProfileRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class MockProfileRepositoryImpl(
    private val api: MockProfileApi
) : ProfileRepository {
    override suspend fun getProfile(): Flow<ProfileEntity> {
        return flow {
            api.getMockProfile()
        }
    }
}