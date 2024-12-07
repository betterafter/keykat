package com.keykat.data.profile.dto

import com.google.gson.annotations.SerializedName

data class TechDto(
    @SerializedName("name")
    val name: String?,

    @SerializedName("icon")
    val icon: String?,

    @SerializedName("content")
    val content: String?,

    @SerializedName("stacks")
    val stacks: List<String>?
)