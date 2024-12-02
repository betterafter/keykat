package com.keykat.data.profile.datasource

import com.keykat.data.profile.dto.EducationDto
import com.keykat.data.profile.dto.ProfileDto
import com.keykat.data.profile.dto.TechDto
import retrofit2.http.GET

interface ProfileApi {
    @GET("/api/v1/profile/profile")
    suspend fun getProfile(): retrofit2.Response<ProfileDto>

    @GET("/api/v1/profile/education")
    suspend fun getEducation(): retrofit2.Response<List<EducationDto>>

    @GET("/api/v1/profile/tech")
    suspend fun getTech(): retrofit2.Response<List<TechDto>>
}