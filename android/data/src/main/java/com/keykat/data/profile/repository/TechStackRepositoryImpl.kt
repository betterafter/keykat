package com.keykat.data.profile.repository

import com.keykat.data.profile.datasource.ProfileApi
import com.keykat.domain.profile.entity.TechEntity
import com.keykat.domain.profile.repository.TechStackRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class TechStackRepositoryImpl @Inject constructor(
    private val profileApi: ProfileApi
) : TechStackRepository {
    override suspend fun getTechStack(): Flow<List<TechEntity>> {
        TODO("Not yet implemented")
    }
}