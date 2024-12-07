package com.keykat.data.career.repository

import com.keykat.data.career.datasource.CareerApi
import com.keykat.domain.career.entity.CareerEntity
import com.keykat.domain.career.repository.CareerRepository
import com.keykat.data.career.dto.CareerMapper.toDomain
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class CareerRepositoryImpl @Inject constructor(
    private val careerApi: CareerApi
) : CareerRepository {
    override suspend fun getCareer(): Flow<List<CareerEntity>> {
        return flow {
            try {
                println("[keykat] ${careerApi.getCareer().body()}")
                val careerEntity = careerApi.getCareer().body()?.mapNotNull {
                    it.toDomain()
                } ?: mutableListOf()

                emit(careerEntity)
            } catch (e: Exception) {
                println("[keykat] e: $e")
                emit(mutableListOf())
            }
        }
    }
}