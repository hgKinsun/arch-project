/*
 * Copyright (c) 2019 Oky Nugroho Kusumo - Open Source Project
 */

package com.okynk.archproject.usecase

import com.okynk.archproject.base.BaseTest
import com.okynk.archproject.core.usecase.general.GeneralUseCase
import org.junit.Test
import org.koin.standalone.inject

class GeneralUseCaseTest : BaseTest() {
    val generalUseCase: GeneralUseCase by inject()

    @Test
    fun getProfiles() {

    }
}