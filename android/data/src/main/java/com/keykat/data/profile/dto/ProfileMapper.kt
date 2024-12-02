package com.keykat.data.profile.dto

import com.keykat.domain.profile.entity.EducationEntity
import com.keykat.domain.profile.entity.ProfileEntity
import com.keykat.domain.profile.entity.SnsEntity
import com.keykat.domain.profile.entity.TechEntity

object ProfileMapper {
    private fun SnsDto.toDomain(): SnsEntity? {

        if (this.name == null) {
            return null
        }

        return SnsEntity(
            name = this.name,
            url = this.url,
            icon = this.icon
        )
    }

    fun ProfileDto.toDomain(): ProfileEntity {
        return ProfileEntity(
            name = this.name,
            tel = this.tel,
            email = this.email,
            profileUrl = this.profileUrl,
            introduce = this.introduce?.replace("\\n", "\n"),
            sns = this.sns?.map { it.toDomain() }?.toList()
        )
    }

    fun EducationDto.toDomain(): EducationEntity? {
        val duration = if (this.startAt != null && this.endAt != null) {
            "${this.startAt} ~ ${this.endAt}"
        } else {
            ""
        }

        if (this.name == null) {
            return null
        }

        return EducationEntity(
            name = this.name,
            where = this.where ?: "",
            duration = duration,
            content = this.content ?: ""
        )
    }

    fun TechDto.toDomain(): TechEntity? {
        if (this.name == null) {
            return null
        }

        return TechEntity(
            name = this.name,
            icon = this.icon,
            content = this.content
        )
    }
}