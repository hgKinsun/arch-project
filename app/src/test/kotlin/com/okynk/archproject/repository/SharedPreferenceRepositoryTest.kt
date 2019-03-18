/*
 * Copyright (c) 2019 Oky Nugroho Kusumo - Open Source Project
 */

package com.okynk.archproject.repository

import com.nhaarman.mockitokotlin2.times
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import com.okynk.archproject.base.BaseTest
import com.okynk.archproject.core.datasource.sharedpreference.SharedPreferenceDataSource
import com.okynk.archproject.core.repository.sharedpreference.SharedPreferenceRepository
import com.okynk.archproject.core.repository.sharedpreference.SharedPreferenceRepositoryImpl
import io.reactivex.Completable
import io.reactivex.Observable
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations

class SharedPreferenceRepositoryTest : BaseTest() {
    private lateinit var mRepository: SharedPreferenceRepository
    @Mock private lateinit var mDataSource: SharedPreferenceDataSource

    @Before
    override fun setUp() {
        super.setUp()
        MockitoAnnotations.initMocks(this)
        mRepository = SharedPreferenceRepositoryImpl(mDataSource)
    }

    @Test
    fun setDummy() {
        val input = "test"

        whenever(mDataSource.setDummy(input)).thenReturn(Completable.complete())

        mRepository.setDummy(input).test().assertResult()

        verify(mDataSource, times(1)).setDummy(input)
    }

    @Test
    fun getDummy() {
        val output = "test"

        whenever(mDataSource.getDummy()).thenReturn(Observable.just(output))

        mRepository.getDummy().test().assertResult(output)

        verify(mDataSource, times(1)).getDummy()
    }
}