package com.okynk.archproject.core.di.module

import com.okynk.archproject.core.datasource.DataSource
import com.okynk.archproject.core.repository.Repository
import com.okynk.archproject.core.repository.RepositoryImpl
import com.okynk.archproject.util.Constants
import dagger.Module
import dagger.Provides
import javax.inject.Named
import javax.inject.Singleton

@Module
class RepositoryModule {

    @Provides
    @Singleton
    fun provideRepository(
        @Named(Constants.DATASOURCE_LOCAL) localDataSource: DataSource,
        @Named(Constants.DATASOURCE_REMOTE) remoteDataSource: DataSource
    ): Repository {
        return RepositoryImpl(localDataSource, remoteDataSource)
    }
}