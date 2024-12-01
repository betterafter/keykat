package com.keykat.data.profile.repository

import com.keykat.data.profile.datasource.ProfileApi
import com.keykat.domain.profile.entity.EducationEntity
import com.keykat.domain.profile.repository.EducationRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class EducationRepositoryImpl @Inject constructor(
    private val profileApi: ProfileApi
) : EducationRepository {
    override suspend fun getEducationList(): Flow<List<EducationEntity>> {
        TODO("Not yet implemented")
    }
}