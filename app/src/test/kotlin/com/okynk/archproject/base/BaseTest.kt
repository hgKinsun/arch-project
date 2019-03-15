/*
 * Copyright (c) 2019 Oky Nugroho Kusumo - Open Source Project
 */

package com.okynk.archproject.base

import com.okynk.archproject.core.rx.SchedulerProvider
import com.okynk.archproject.injection.testRxModule
import org.junit.After
import org.junit.Before
import org.koin.standalone.StandAloneContext.startKoin
import org.koin.standalone.StandAloneContext.stopKoin
import org.koin.standalone.inject
import org.koin.test.KoinTest

abstract class BaseTest : KoinTest {

    protected val testScheduler: SchedulerProvider by inject()

    @Before
    open fun setUp() {
        startKoin(listOf(testRxModule))
    }

    @After
    open fun tearDown() {
        stopKoin()
    }
}