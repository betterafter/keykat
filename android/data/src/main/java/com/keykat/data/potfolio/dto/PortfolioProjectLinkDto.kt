package com.keykat.data.potfolio.dto

import com.google.gson.annotations.SerializedName

data class PortfolioProjectLinkDto(
    @SerializedName("link")
    val link: String?,

    @SerializedName("icon")
    val icon: String?
)