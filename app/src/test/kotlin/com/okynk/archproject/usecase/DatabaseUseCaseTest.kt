/*
 * Copyright (c) 2019 Oky Nugroho Kusumo - Open Source Project
 */

package com.okynk.archproject.usecase

import com.nhaarman.mockitokotlin2.times
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import com.okynk.archproject.base.BaseTest
import com.okynk.archproject.core.repository.database.DatabaseRepository
import com.okynk.archproject.core.usecase.database.DatabaseUseCase
import io.reactivex.Completable
import org.junit.Before
import org.junit.Test
import org.koin.standalone.inject
import org.koin.test.declareMock

class DatabaseUseCaseTest : BaseTest() {
    private val mUseCase: DatabaseUseCase by inject()
    private val mRepository: DatabaseRepository by inject()

    @Before
    override fun setUp() {
        super.setUp()
        declareMock<DatabaseRepository>()
    }

    @Test
    fun clear() {
        whenever(mRepository.clear()).thenReturn(Completable.complete())

        mUseCase.clear().test()
            .assertResult()

        verify(mRepository, times(1)).clear()
    }
}