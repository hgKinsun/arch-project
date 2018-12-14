/*
 * Copyright (c) 2018 Oky Nugroho Kusumo - Open Source Project
 */

package com.okynk.archproject.core.di.component

import android.content.Context
import com.okynk.archproject.core.di.module.ApiModule
import com.okynk.archproject.core.di.module.ApplicationModule
import com.okynk.archproject.core.di.module.DataSourceModule
import com.okynk.archproject.core.di.module.MapperModule
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [ApplicationModule::class, ApiModule::class, MapperModule::class, DataSourceModule::class])
interface ApplicationComponent {

    fun applicationContext(): Context
}