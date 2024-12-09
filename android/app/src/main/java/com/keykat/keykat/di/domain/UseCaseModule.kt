package com.keykat.keykat.di.domain

import com.keykat.domain.career.repository.CareerRepository
import com.keykat.domain.potfolio.repository.PortfolioRepository
import com.keykat.domain.profile.repository.EducationRepository
import com.keykat.domain.profile.repository.ProfileRepository
import com.keykat.domain.profile.repository.TechStackRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import usecase.CareerUseCase
import usecase.PortfolioUseCase
import usecase.ProfileUseCase
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object UseCaseModule {

    @Singleton
    @Provides
    fun provideProfileUseCase(
        profileRepository: ProfileRepository,
        educationRepository: EducationRepository,
        techStackRepository: TechStackRepository
    ): ProfileUseCase {
        return ProfileUseCase(
            profileRepository,
            educationRepository,
            techStackRepository
        )
    }

    @Singleton
    @Provides
    fun provideCareerUseCase(
        careerRepository: CareerRepository
    ): CareerUseCase {
        return CareerUseCase(
            careerRepository
        )
    }

    @Singleton
    @Provides
    fun providePortfolioUseCase(
        portfolioRepository: PortfolioRepository
    ): PortfolioUseCase {
        return PortfolioUseCase(
            portfolioRepository
        )
    }
}