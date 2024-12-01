package com.keykat.data.profile.repository

import com.keykat.data.profile.datasource.ProfileApi
import com.keykat.domain.profile.entity.ProfileEntity
import com.keykat.domain.profile.repository.ProfileRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ProfileRepositoryImpl @Inject constructor(
    private val profileApi: ProfileApi
) : ProfileRepository {
    override suspend fun getProfile(): Flow<ProfileEntity> {
        TODO("Not yet implemented")
    }

}