package com.keykat.data.profile.dto

import com.google.gson.annotations.SerializedName

data class ProfileDto(
    @SerializedName("name")
    val name: String?,

    @SerializedName("tel")
    val tel: String?,

    @SerializedName("email")
    val email: String?,

    @SerializedName("profile_url")
    val profileUrl: String?,

    @SerializedName("introduce")
    val introduce: String?,

    @SerializedName("sns")
    val sns: List<SnsDto>?,

    @SerializedName("tech")
    val tech: List<TechDto>?,

    @SerializedName("education")
    val education: List<EducationDto>?
)