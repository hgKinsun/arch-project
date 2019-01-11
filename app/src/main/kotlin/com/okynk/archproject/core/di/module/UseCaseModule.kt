package com.okynk.archproject.core.di.module

import com.okynk.archproject.core.repository.Repository
import com.okynk.archproject.core.usecase.UseCase
import com.okynk.archproject.core.usecase.UseCaseImpl
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class UseCaseModule {
    @Provides
    @Singleton
    fun provideUseCase(repository: Repository): UseCase {
        return UseCaseImpl(repository)
    }
}