/*
 * Copyright (c) 2018 Oky Nugroho Kusumo - Open Source Project
 */

package com.okynk.archproject.core.di.module

import com.okynk.archproject.core.api.ApiService
import com.okynk.archproject.core.api.model.response.ListWrapperResponse
import com.okynk.archproject.core.api.model.response.ProfileResponse
import com.okynk.archproject.core.datasource.DataSource
import com.okynk.archproject.core.datasource.LocalDataSource
import com.okynk.archproject.core.datasource.RemoteDataSource
import com.okynk.archproject.core.entity.PaginatedListEntity
import com.okynk.archproject.core.entity.ProfileEntity
import com.okynk.archproject.core.mapper.Mapper
import com.okynk.archproject.core.storage.model.ProfileDbModel
import com.okynk.archproject.core.storage.realm.RealmStorage
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
    fun provideLocalDataSource(
        realmStorage: RealmStorage,
        @Named(Mapper.PROFILE_ENTITY_TO_DB) profileEntityDbMapper: Mapper<ProfileEntity, ProfileDbModel>,
        @Named(Mapper.PROFILE_DB_TO_ENTITY) profileDbEntityMapper: Mapper<ProfileDbModel, ProfileEntity>
    ): DataSource {
        return LocalDataSource(realmStorage, profileEntityDbMapper, profileDbEntityMapper)
    }

    @Provides
    @Singleton
    @Named(Constants.DATASOURCE_REMOTE)
    fun provideRemoteDataSource(
        apiService: ApiService,
        @Named(Mapper.PROFILE_LIST_RESPONSE_TO_ENTITY) profileListResponseEntityMapper: Mapper<ListWrapperResponse<ProfileResponse>, PaginatedListEntity<ProfileEntity>>,
        @Named(Mapper.PROFILE_RESPONSE_TO_ENTITY) profileResponseEntityMapper: Mapper<ProfileResponse, ProfileEntity>
    ): DataSource {
        return RemoteDataSource(apiService, profileListResponseEntityMapper, profileResponseEntityMapper)
    }
}