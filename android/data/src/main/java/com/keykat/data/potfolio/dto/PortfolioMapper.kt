package com.keykat.data.potfolio.dto

import com.keykat.domain.potfolio.entity.PortfolioCareerEntity
import com.keykat.domain.potfolio.entity.PortfolioEntity
import com.keykat.domain.potfolio.entity.PortfolioProjectEntity
import com.keykat.domain.potfolio.entity.PortfolioProjectLinkEntity

object PortfolioMapper {
    fun PortfolioDto.toDomain(): PortfolioEntity {
        return PortfolioEntity(
            portfolioProjectEntity = this.portfolioProjectDto.mapNotNull {
                it.toDomain()
            },
            portfolioCareerEntity = this.portfolioCareerDto.mapNotNull {
                it.toDomain()
            }
        )
    }

    private fun PortfolioCareerDto.toDomain(): PortfolioCareerEntity? {
        if (name == null) {
            return null
        }

        return PortfolioCareerEntity(
            name = this.name,
            duration = "${this.startAt} ~ ${this.endAt}",
            introduce = this.introduce,
            url = this.url,
            links = this.links?.map {
                it.toDomain()
            },
            parent = this.parent,
        )
    }

    private fun PortfolioProjectDto.toDomain(): PortfolioProjectEntity? {
        if (name == null) {
            return null
        }

        return PortfolioProjectEntity(
            name = this.name,
            introduce = this.introduce,
            url = this.url,
            work = this.work,
            thumbnail = this.thumbnail
        )
    }

    private fun PortfolioProjectLinkDto.toDomain(): PortfolioProjectLinkEntity {
        return PortfolioProjectLinkEntity(
            icon = this.icon,
            link = this.link
        )
    }
}