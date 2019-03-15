/*
 * Copyright (c) 2019 Oky Nugroho Kusumo - Open Source Project
 */

package com.okynk.archproject.usecase

import com.nhaarman.mockitokotlin2.times
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import com.okynk.archproject.base.BaseTest
import com.okynk.archproject.core.repository.sharedpreference.SharedPreferenceRepository
import com.okynk.archproject.core.usecase.sharedpreference.SharedPreferenceUseCase
import com.okynk.archproject.core.usecase.sharedpreference.SharedPreferenceUseCaseImpl
import io.reactivex.Completable
import io.reactivex.Observable
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations

class SharedPreferenceUseCaseTest : BaseTest() {
    private lateinit var mUseCase: SharedPreferenceUseCase
    @Mock private lateinit var mRepository: SharedPreferenceRepository

    @Before
    override fun setUp() {
        super.setUp()
        MockitoAnnotations.initMocks(this)
        mUseCase = SharedPreferenceUseCaseImpl(mRepository, testScheduler)
    }

    @Test
    fun setDummy() {
        val str = "test"

        whenever(mRepository.setDummy(str)).thenReturn(Completable.complete())

        mUseCase.setDummy(str)
            .test()
            .assertResult()

        verify(mRepository, times(1)).setDummy(str)
    }

    @Test
    fun getDummy() {
        val expectedResult = "result"

        whenever(mRepository.getDummy()).thenReturn(Observable.just(expectedResult))

        mUseCase.getDummy().test().assertResult(expectedResult)

        verify(mRepository, times(1)).getDummy()
    }
}