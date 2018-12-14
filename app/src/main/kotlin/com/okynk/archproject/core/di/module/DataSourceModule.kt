/*
 * Copyright (c) 2018 Oky Nugroho Kusumo - Open Source Project
 */

package com.okynk.archproject.core.di.module

import com.okynk.archproject.core.api.ApiService
import com.okynk.archproject.core.datasource.DataSource
import com.okynk.archproject.core.datasource.LocalDataSource
import com.okynk.archproject.core.datasource.RemoteDataSource
import com.okynk.archproject.core.mapper.ListProfileResponseEntityMapper
import com.okynk.archproject.util.Constants
import dagger.Module
import dagger.Provides
import javax.inject.Named
import javax.inject.Singleton

@Module
class DataSourceModule {
    @Provides
    @Singleton
    @Named(Constants.DATASOURCE_LOCAL)
    fun provideLocalDataSource(): DataSource {
        return LocalDataSource()
    }

    @Provides
    @Singleton
    @Named(Constants.DATASOURCE_REMOTE)
    fun provideRemoteDataSource(
        apiService: ApiService,
        listProfileResponseEntityMapper: ListProfileResponseEntityMapper
    ): DataSource {
        return RemoteDataSource(apiService, listProfileResponseEntityMapper)
    }
}