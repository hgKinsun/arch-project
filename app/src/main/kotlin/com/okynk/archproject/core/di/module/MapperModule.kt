/*
 * Copyright (c) 2018 Oky Nugroho Kusumo - Open Source Project
 */

package com.okynk.archproject.core.di.module

import com.okynk.archproject.core.api.model.response.ListWrapperResponse
import com.okynk.archproject.core.api.model.response.ProfileResponse
import com.okynk.archproject.core.entity.PaginatedListEntity
import com.okynk.archproject.core.entity.ProfileEntity
import com.okynk.archproject.core.mapper.*
import com.okynk.archproject.core.storage.model.PaginatedListDbModel
import com.okynk.archproject.core.storage.model.ProfileDbModel
import dagger.Module
import dagger.Provides
import javax.inject.Named
import javax.inject.Singleton

@Module
class MapperModule {
    @Provides
    @Singleton
    @Named(Mapper.PROFILE_RESPONSE_TO_ENTITY)
    fun provideProfileResponseEntityMapper(): Mapper<ProfileResponse, ProfileEntity> {
        return ProfileResponseEntityMapper()
    }

    @Provides
    @Singleton
    @Named(Mapper.PROFILE_LIST_RESPONSE_TO_ENTITY)
    fun provideProfileListResponseEntityMapper(@Named(Mapper.PROFILE_RESPONSE_TO_ENTITY) profileMapper: Mapper<ProfileResponse, ProfileEntity>): Mapper<ListWrapperResponse<ProfileResponse>, PaginatedListEntity<ProfileEntity>> {
        return ProfileListResponseEntityMapper(profileMapper)
    }

    @Provides
    @Singleton
    @Named(Mapper.PROFILE_ENTITY_TO_DB)
    fun provideProfileEntityDbModelMapper(): Mapper<ProfileEntity, ProfileDbModel> {
        return ProfileEntityDbModelMapper()
    }

    @Provides
    @Singleton
    @Named(Mapper.PROFILE_LIST_ENTITY_TO_DB)
    fun provideProfileListEntityDbModelMapper(@Named(Mapper.PROFILE_ENTITY_TO_DB) mapper: Mapper<ProfileEntity, ProfileDbModel>): Mapper<PaginatedListEntity<ProfileEntity>, PaginatedListDbModel<ProfileDbModel>> {
        return ProfileListEntityDbModelMapper(mapper)
    }

    @Provides
    @Singleton
    @Named(Mapper.PROFILE_DB_TO_ENTITY)
    fun provideProfileDbModelEntityMapper(): Mapper<ProfileDbModel, ProfileEntity> {
        return ProfileDbModelEntityMapper()
    }

    @Provides
    @Singleton
    @Named(Mapper.PROFILE_LIST_DB_TO_ENTITY)
    fun provideProfileListDbModelEntityMapper(@Named(Mapper.PROFILE_DB_TO_ENTITY) mapper: Mapper<ProfileDbModel, ProfileEntity>): Mapper<PaginatedListDbModel<ProfileDbModel>, PaginatedListEntity<ProfileEntity>> {
        return ProfileListDbModelEntityMapper(mapper)
    }
}