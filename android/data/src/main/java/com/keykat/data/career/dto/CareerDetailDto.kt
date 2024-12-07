package com.keykat.data.career.dto

import com.google.gson.annotations.SerializedName

data class CareerDetailDto(
    @SerializedName("Project_name")
    val name: String?,

    @SerializedName("start_at")
    val startAt: String?,

    @SerializedName("end_at")
    val endAt: String?,

    @SerializedName("description")
    val description: String?
)