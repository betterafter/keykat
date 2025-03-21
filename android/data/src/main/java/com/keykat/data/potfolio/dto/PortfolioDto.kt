package com.keykat.data.potfolio.dto

import com.google.gson.annotations.SerializedName

data class PortfolioDto(
    @SerializedName("project")
    val portfolioProjectDto: List<PortfolioProjectDto>,

    @SerializedName("career")
    val portfolioCareerDto: List<PortfolioCareerDto>
)