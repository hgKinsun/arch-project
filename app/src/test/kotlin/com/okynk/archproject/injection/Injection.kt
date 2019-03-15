/*
 * Copyright (c) 2019 Oky Nugroho Kusumo - Open Source Project
 */

package com.okynk.archproject.injection

import android.app.Application
import android.content.Context
import com.okynk.archproject.core.rx.SchedulerProvider
import com.okynk.archproject.rx.TestSchedulerProviderImpl
import io.reactivex.schedulers.TestScheduler
import org.koin.dsl.module.module
import org.mockito.Mockito

val testModule = module {
    single { Mockito.mock(Application::class.java) }
    single { Mockito.mock(Context::class.java) }
}

val testRxModule = module {
    single { TestScheduler() }
    single<SchedulerProvider> { TestSchedulerProviderImpl(get()) }
}