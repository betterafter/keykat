package com.keykat.domain.potfolio.entity

data class PortfolioCareerEntity(
    val name: String?,
    val duration: String?,
    val introduce: String?,
    val url: String?,
    val thumbnail: String?,
    val parent: String?,
    val background: String?,
    var children: MutableList<PortfolioCareerEntity> = mutableListOf(),
    val links: List<PortfolioProjectLinkEntity>?
)