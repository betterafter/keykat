package com.keykat.domain.profile.repository

import com.keykat.domain.profile.entity.ProfileEntity
import kotlinx.coroutines.flow.Flow

interface ProfileRepository {
    suspend fun getProfile(): Flow<ProfileEntity?>
}