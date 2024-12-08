package com.keykat.data.career.datasource

import com.keykat.data.career.dto.CareerDto
import retrofit2.http.GET

interface CareerApi {
    @GET("/api/v1/career")
    suspend fun getCareer(): retrofit2.Response<List<CareerDto>>
}