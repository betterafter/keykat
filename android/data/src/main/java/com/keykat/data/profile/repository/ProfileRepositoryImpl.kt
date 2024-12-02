package com.keykat.data.profile.repository

import com.keykat.data.profile.datasource.ProfileApi
import com.keykat.data.profile.dto.ProfileMapper.toDomain
import com.keykat.domain.profile.entity.ProfileEntity
import com.keykat.domain.profile.repository.ProfileRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class ProfileRepositoryImpl @Inject constructor(
    private val profileApi: ProfileApi
) : ProfileRepository {
    override suspend fun getProfile(): Flow<ProfileEntity?> {
        return flow {
            try {
                val profileEntity = profileApi.getProfile().body()?.toDomain()
                emit(profileEntity)
            } catch (e: Exception) {
                emit(null)
            }
        }
    }

}