package com.keykat.domain.profile.entity

import com.google.gson.annotations.SerializedName

data class SnsEntity(
    val name: String,
    val url: String?,
    val icon: String?
)