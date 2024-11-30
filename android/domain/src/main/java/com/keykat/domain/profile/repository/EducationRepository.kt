package com.keykat.domain.profile.repository

import com.keykat.domain.profile.entity.EducationEntity
import kotlinx.coroutines.flow.Flow

interface EducationRepository {
    suspend fun getEducationList(): Flow<List<EducationEntity>>
}