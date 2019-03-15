/*
 * Copyright (c) 2019 Oky Nugroho Kusumo - Open Source Project
 */

package com.okynk.archproject.base

import com.okynk.archproject.appModules
import com.okynk.archproject.injection.testModule
import com.okynk.archproject.injection.testRxModule
import org.junit.After
import org.junit.Before
import org.koin.standalone.StandAloneContext.startKoin
import org.koin.standalone.StandAloneContext.stopKoin
import org.koin.test.KoinTest

abstract class BaseTest : KoinTest {

    @Before
    fun setUp() {
        startKoin(appModules + testModule + testRxModule)
    }

    @After
    fun tearDown() {
        stopKoin()
    }
}