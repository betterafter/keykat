package com.keykat.data.potfolio.dto

import com.google.gson.annotations.SerializedName


data class PortfolioProjectDto(
    @SerializedName("name")
    val name: String?,

    @SerializedName("introduce")
    val introduce: String?,

    @SerializedName("url")
    val url: String?,

    @SerializedName("work")
    val work: String?,

    @SerializedName("thumbnail")
    val thumbnail: String,

)