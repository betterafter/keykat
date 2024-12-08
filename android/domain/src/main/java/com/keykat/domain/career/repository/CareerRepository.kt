package com.keykat.domain.career.repository

import com.keykat.domain.career.entity.CareerEntity
import kotlinx.coroutines.flow.Flow


interface CareerRepository {
    suspend fun getCareer(): Flow<List<CareerEntity>>
}