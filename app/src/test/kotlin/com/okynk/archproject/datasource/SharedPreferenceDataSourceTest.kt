/*
 * Copyright (c) 2019 Oky Nugroho Kusumo - Open Source Project
 */

package com.okynk.archproject.datasource

import com.nhaarman.mockitokotlin2.times
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import com.okynk.archproject.base.BaseTest
import com.okynk.archproject.core.datasource.sharedpreference.SharedPreferenceDataSource
import com.okynk.archproject.core.datasource.sharedpreference.SharedPreferenceDataSourceImpl
import com.okynk.archproject.core.storage.sharedpreference.SharedPreferenceStorage
import io.reactivex.Completable
import io.reactivex.Observable
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations

class SharedPreferenceDataSourceTest : BaseTest() {
    private lateinit var mDataSource: SharedPreferenceDataSource
    @Mock private lateinit var mStorage: SharedPreferenceStorage

    @Before
    override fun setUp() {
        super.setUp()
        MockitoAnnotations.initMocks(this)
        mDataSource = SharedPreferenceDataSourceImpl(mStorage)
    }

    @Test
    fun setDummy() {
        val input = "test"

        whenever(mStorage.setDummy(input)).thenReturn(Completable.complete())

        mDataSource.setDummy(input).test().assertResult()

        verify(mStorage, times(1)).setDummy(input)
    }

    @Test
    fun getDummy() {
        val output = "test"

        whenever(mStorage.getDummy()).thenReturn(Observable.just(output))

        mDataSource.getDummy().test().assertResult(output)

        verify(mStorage, times(1)).getDummy()
    }
}