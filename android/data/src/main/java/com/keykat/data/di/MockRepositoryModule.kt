package com.keykat.data.di

import com.keykat.data.profile.repository.MockEducationRepositoryImpl
import com.keykat.data.profile.repository.MockProfileRepositoryImpl
import com.keykat.data.profile.repository.MockTechStackRepositoryImpl
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
abstract class MockRepositoryModule {
    // @Binds: 생성자를 가지지 않는 인터페이스 의존성 삽입의 경우
    @Singleton
    @Binds
    abstract fun bindsProfileRepository(
        profileRepositoryImpl: MockProfileRepositoryImpl
    ): ProfileRepository

    @Singleton
    @Binds
    abstract fun bindsEducationRepository(
        educationRepositoryImpl: MockEducationRepositoryImpl
    ): EducationRepository

    @Singleton
    @Binds
    abstract fun bindsTechRepository(
        techStackRepositoryImpl: MockTechStackRepositoryImpl
    ): TechStackRepository
}