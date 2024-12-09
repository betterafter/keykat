package com.keykat.domain.potfolio.entity

data class PortfolioEntity(
    val portfolioCareerEntity: List<PortfolioCareerEntity>?,
    val portfolioProjectEntity: List<PortfolioProjectEntity>?
)