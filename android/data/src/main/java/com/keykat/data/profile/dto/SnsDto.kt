package com.keykat.data.profile.dto

import com.google.gson.annotations.SerializedName

data class SnsDto(
    @SerializedName("name")
    val name: String?,

    @SerializedName("url")
    val url: String?,

    @SerializedName("icon")
    val icon: String?,

    @SerializedName("web_url")
    val webUrl: String?,
)