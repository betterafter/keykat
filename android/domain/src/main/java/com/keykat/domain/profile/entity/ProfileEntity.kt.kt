package com.keykat.domain.profile.entity

data class ProfileEntity(
    val name: String?,
    val tel: String?,
    val email: String?,
    val profileUrl: String?,
    val introduce: String?,
    val sns: List<SnsEntity?>?
)
