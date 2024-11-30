package com.keykat.data.profile.repository

import com.keykat.domain.profile.entity.ProfileEntity
import com.keykat.domain.profile.repository.ProfileRepository
import kotlinx.coroutines.flow.Flow

class ProfileRepositoryImpl : ProfileRepository {
    override suspend fun getProfile(): Flow<ProfileEntity> {
        TODO("Not yet implemented")
    }

}