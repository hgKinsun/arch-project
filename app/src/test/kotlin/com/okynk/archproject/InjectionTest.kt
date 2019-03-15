/*
 * Copyright (c) 2019 Oky Nugroho Kusumo - Open Source Project
 */

package com.okynk.archproject

import com.okynk.archproject.injection.testModule
import org.junit.Test
import org.koin.test.KoinTest
import org.koin.test.checkModules

class InjectionTest : KoinTest {

    @Test
    fun testInjection() {
        checkModules(appModules + testModule)
    }
}