package com.keykat.domain.career.entity

data class CareerEntity(
    val name: String?,
    val position: String?,
    val role: String?,
    val duration: String?,
    val teamName: String?,
    val careerDetail: List<CareerDetailEntity>
)