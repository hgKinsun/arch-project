/*
 * Copyright (c) 2018 Oky Nugroho Kusumo - Open Source Project
 */

package com.okynk.archproject.core.di.component

import android.content.Context
import com.okynk.archproject.core.di.module.*
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        ApplicationModule::class,
        ApiModule::class,
        MapperModule::class,
        DataSourceModule::class,
        StorageModule::class,
        RepositoryModule::class,
        UseCaseModule::class
    ]
)
interface ApplicationComponent {

    fun applicationContext(): Context
}