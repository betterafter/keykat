package com.keykat.data.career.repository

import android.icu.text.DateFormat
import android.icu.text.SimpleDateFormat
import android.os.Build
import androidx.annotation.RequiresApi
import com.keykat.data.career.datasource.CareerApi
import com.keykat.domain.career.entity.CareerEntity
import com.keykat.domain.career.repository.CareerRepository
import com.keykat.data.career.dto.CareerMapper.toDomain
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.ZoneOffset
import java.time.format.DateTimeFormatter
import javax.inject.Inject

class CareerRepositoryImpl @Inject constructor(
    private val careerApi: CareerApi
) : CareerRepository {
    @RequiresApi(Build.VERSION_CODES.O)
    override suspend fun getCareer(): Flow<List<CareerEntity>> {
        return flow {
            try {
                val careerDto = careerApi.getCareer().body()?.sortedByDescending {
                    LocalDate.parse(
                        it.endAt,
                        DateTimeFormatter.ofPattern("yyyy.MM.dd")
                    )
                }

                val careerEntity = careerDto?.mapNotNull {
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