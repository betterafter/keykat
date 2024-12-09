package com.keykat.domain.potfolio.entity

data class PortfolioCareerEntity(
    val name: String?,
    val duration: String?,
    val introduce: String?,
    val url: String?,
    val parent: String?,
    val links: List<PortfolioProjectLinkEntity>?
)