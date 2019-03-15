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
import com.okynk.archproject.core.usecase.database.DatabaseUseCaseImpl
import io.reactivex.Completable
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations

class DatabaseUseCaseTest : BaseTest() {
    private lateinit var mUseCase: DatabaseUseCase
    @Mock private lateinit var mRepository: DatabaseRepository

    @Before
    override fun setUp() {
        super.setUp()
        MockitoAnnotations.initMocks(this)
        mUseCase = DatabaseUseCaseImpl(mRepository, testScheduler)
    }

    @Test
    fun clear() {
        whenever(mRepository.clear()).thenReturn(Completable.complete())

        mUseCase.clear().test()
            .assertResult()

        verify(mRepository, times(1)).clear()
    }
}