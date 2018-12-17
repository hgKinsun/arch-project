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
import com.okynk.archproject.core.storage.model.PaginatedListDbModel
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
        @Named(Mapper.PROFILE_LIST_ENTITY_TO_DB) listEntityDbMapper: Mapper<PaginatedListEntity<ProfileEntity>, PaginatedListDbModel<ProfileDbModel>>,
        @Named(Mapper.PROFILE_LIST_DB_TO_ENTITY) listDbEntityMapper: Mapper<PaginatedListDbModel<ProfileDbModel>, PaginatedListEntity<ProfileEntity>>
    ): DataSource {
        return LocalDataSource(realmStorage, listEntityDbMapper, listDbEntityMapper)
    }

    @Provides
    @Singleton
    @Named(Constants.DATASOURCE_REMOTE)
    fun provideRemoteDataSource(
        apiService: ApiService,
        @Named(Mapper.PROFILE_LIST_RESPONSE_TO_ENTITY) profileListResponseEntityMapper: Mapper<ListWrapperResponse<ProfileResponse>, PaginatedListEntity<ProfileEntity>>
    ): DataSource {
        return RemoteDataSource(apiService, profileListResponseEntityMapper)
    }
}