/*
 * Copyright (c) 2019 Oky Nugroho Kusumo - Open Source Project
 */

package com.okynk.archproject.rx

import com.okynk.archproject.core.rx.SchedulerProvider
import io.reactivex.schedulers.Schedulers
import io.reactivex.schedulers.TestScheduler

class TestSchedulerProviderImpl(private val testScheduler: TestScheduler) : SchedulerProvider {
    override fun io() = Schedulers.trampoline()

    override fun ui() = Schedulers.trampoline()

    override fun computation() = Schedulers.trampoline()
}