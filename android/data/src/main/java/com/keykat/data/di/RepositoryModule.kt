package com.keykat.data.di

import com.keykat.data.profile.repository.EducationRepositoryImpl
import com.keykat.data.profile.repository.ProfileRepositoryImpl
import com.keykat.data.profile.repository.TechStackRepositoryImpl
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
}