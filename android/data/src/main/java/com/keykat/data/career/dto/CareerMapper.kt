package com.keykat.data.career.dto

import com.keykat.domain.career.entity.CareerDetailEntity
import com.keykat.domain.career.entity.CareerEntity

object CareerMapper {
    fun CareerDto.toDomain(): CareerEntity? {
        if (this.name == null) {
            return null
        }

        return CareerEntity(
            name = this.name,
            position = this.position,
            role = this.role,
            duration = "${this.startAt} ~ ${this.endAt}",
            teamName = this.teamName,
            careerDetail = this.careerDetail.mapNotNull {
                it.toDomain()
            }
        )
    }

    private fun CareerDetailDto.toDomain(): CareerDetailEntity? {
        if (this.name == null) {
            return null
        }

        return CareerDetailEntity(
            name = this.name,
            duration = "${this.startAt} ~ ${this.endAt}",
            description = this.description?.replace("\\n", "\n")
        )
    }
}