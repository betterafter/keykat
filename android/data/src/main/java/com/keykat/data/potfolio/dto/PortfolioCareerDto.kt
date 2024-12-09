package com.keykat.data.potfolio.dto

import com.google.gson.annotations.SerializedName

data class PortfolioCareerDto(
    @SerializedName("name")
    val name: String?,

    @SerializedName("start_at")
    val startAt: String?,

    @SerializedName("end_at")
    val endAt: String?,

    @SerializedName("introduce")
    val introduce: String?,

    @SerializedName("url")
    val url: String?,

    @SerializedName("parent")
    val parent: String?,

    @SerializedName("thumbnail")
    val thumbnail: String,

    @SerializedName("links")
    val links: List<PortfolioProjectLinkDto>?
)