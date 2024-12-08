package com.keykat.data.career.dto

import android.graphics.Color
import android.os.Build
import androidx.annotation.RequiresApi
import com.keykat.domain.career.entity.CareerDetailEntity
import com.keykat.domain.career.entity.CareerEntity
import java.time.LocalDate
import java.time.format.DateTimeFormatter

object CareerMapper {
    @RequiresApi(Build.VERSION_CODES.O)
    fun CareerDto.toDomain(): CareerEntity? {
        if (this.name == null) {
            return null
        }

        val color = if (this.mainColor != null) {
            Color.parseColor(this.mainColor)
        } else {
            Color.BLUE
        }

        val formatter = DateTimeFormatter.ofPattern("yyyy.MM.dd")
        val detail = this.careerDetail.sortedByDescending { dto ->
            LocalDate.parse("${dto.endAt}.01", formatter)
        }

        return CareerEntity(
            name = this.name,
            position = this.position,
            role = this.role,
            duration = "${this.startAt} ~ ${this.endAt}",
            teamName = this.teamName,
            mainColor = color,
            careerDetail = detail.mapNotNull {
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
            description = this.description?.replace("\\n", "\n"),
            introduce = this.introduce,
            techStack = this.techStack,
        )
    }
}