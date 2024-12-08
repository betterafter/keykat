package com.keykat.data.career.dto

import com.google.gson.annotations.SerializedName

data class CareerDto(
    @SerializedName("name")
    val name: String?,

    @SerializedName("position")
    val position: String?,

    @SerializedName("role")
    val role: String?,

    @SerializedName("start_at")
    val startAt: String?,

    @SerializedName("end_at")
    val endAt: String?,

    @SerializedName("team_name")
    val teamName: String?,

    @SerializedName("main_color")
    val mainColor: String?,

    @SerializedName("career_detail")
    val careerDetail: List<CareerDetailDto>
)