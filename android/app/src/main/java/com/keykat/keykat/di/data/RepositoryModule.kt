package com.keykat.keykat.di.data

import com.keykat.data.career.repository.CareerRepositoryImpl
import com.keykat.data.potfolio.repository.PortfolioRepositoryImpl
import com.keykat.data.profile.repository.EducationRepositoryImpl
import com.keykat.data.profile.repository.ProfileRepositoryImpl
import com.keykat.data.profile.repository.TechStackRepositoryImpl
import com.keykat.domain.career.repository.CareerRepository
import com.keykat.domain.potfolio.repository.PortfolioRepository
import com.keykat.domain.profile.repository.EducationRepository
import com.keykat.domain.profile.repository.ProfileRepository
import com.keykat.domain.profile.repository.TechStackRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {
    // @Binds: 생성자를 가지지 않는 인터페이스 의존성 삽입의 경우
    @Singleton
    @Binds
    abstract fun bindsProfileRepository(
        profileRepositoryImpl: ProfileRepositoryImpl
    ): ProfileRepository

    @Singleton
    @Binds
    abstract fun bindsEducationRepository(
        educationRepositoryImpl: EducationRepositoryImpl
    ): EducationRepository

    @Singleton
    @Binds
    abstract fun bindsTechRepository(
        techStackRepositoryImpl: TechStackRepositoryImpl
    ): TechStackRepository

    @Singleton
    @Binds
    abstract fun bindsCareerRepository(
        careerRepositoryImpl: CareerRepositoryImpl
    ): CareerRepository

    @Singleton
    @Binds
    abstract fun bindsPortfolioRepository(
        portfolioRepositoryImpl: PortfolioRepositoryImpl
    ): PortfolioRepository
}