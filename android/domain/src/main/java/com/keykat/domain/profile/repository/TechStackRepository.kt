package com.keykat.domain.profile.repository

import com.keykat.domain.profile.entity.TechEntity
import kotlinx.coroutines.flow.Flow

interface TechStackRepository {
    suspend fun getTechStack(): Flow<List<TechEntity>>
}