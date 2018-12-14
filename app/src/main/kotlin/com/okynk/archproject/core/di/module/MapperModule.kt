/*
 * Copyright (c) 2018 Oky Nugroho Kusumo - Open Source Project
 */

package com.okynk.archproject.core.di.module

import com.okynk.archproject.core.mapper.ListProfileResponseEntityMapper
import com.okynk.archproject.core.mapper.ProfileResponseEntityMapper
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class MapperModule {
    @Provides
    @Singleton
    fun provideProfileResponseEntityMapper(): ProfileResponseEntityMapper {
        return ProfileResponseEntityMapper()
    }

    @Provides
    @Singleton
    fun provideListProfileResponseEntityMapper(profileMapper: ProfileResponseEntityMapper): ListProfileResponseEntityMapper {
        return ListProfileResponseEntityMapper(profileMapper)
    }
}