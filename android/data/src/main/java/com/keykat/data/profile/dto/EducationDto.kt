package com.keykat.data.profile.dto

import com.google.gson.annotations.SerializedName

data class EducationDto(
    @SerializedName("name")
    val name: String?,

    @SerializedName("where")
    val where: String?,

    @SerializedName("start_at")
    val startAt: String?,

    @SerializedName("end_at")
    val endAt: String?,

    @SerializedName("content")
    val content: String?
)