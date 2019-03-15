/*
 * Copyright (c) 2019 Oky Nugroho Kusumo - Open Source Project
 */

package com.okynk.archproject.injection

import com.okynk.archproject.core.rx.SchedulerProvider
import com.okynk.archproject.core.rx.SchedulerProviderImpl
import org.koin.dsl.module.module

val rxModule = module {
    single<SchedulerProvider> { SchedulerProviderImpl() }
}