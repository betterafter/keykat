package com.keykat.domain.potfolio.entity

import com.google.gson.annotations.SerializedName

data class PortfolioProjectLinkEntity(
    @SerializedName("link")
    val link: String?,

    @SerializedName("icon")
    val icon: String?
)