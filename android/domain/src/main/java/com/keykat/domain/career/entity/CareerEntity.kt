package com.keykat.domain.career.entity

import android.graphics.Color

data class CareerEntity(
    val name: String?,
    val position: String?,
    val role: String?,
    val duration: String?,
    val teamName: String?,
    val mainColor: Int,
    val careerDetail: List<CareerDetailEntity>
)