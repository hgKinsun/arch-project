/*
 * Copyright (c) 2019 Oky Nugroho Kusumo - Open Source Project
 */

package com.okynk.archproject.storage

import com.nhaarman.mockitokotlin2.times
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import com.okynk.archproject.base.BaseTest
import com.okynk.archproject.core.storage.sharedpreference.SharedPreferenceStorage
import com.okynk.archproject.core.storage.sharedpreference.SharedPreferenceStorageImpl
import com.okynk.archproject.core.wrapper.hawk.HawkWrapper
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations

class SharedPreferenceStorageTest : BaseTest() {
    private lateinit var mStorage: SharedPreferenceStorage
    @Mock private lateinit var mHawkWrapper: HawkWrapper

    @Before
    override fun setUp() {
        super.setUp()
        MockitoAnnotations.initMocks(this)
        mStorage = SharedPreferenceStorageImpl(mHawkWrapper)
    }

    @Test
    fun clear() {
        whenever(mHawkWrapper.deleteAll()).thenReturn(true)

        mStorage.clear().test()
            .assertResult()

        verify(mHawkWrapper, times(1)).deleteAll()
    }

    @Test
    fun getDummy() {
        val result = "test"

        whenever(mHawkWrapper.get<String>(SharedPreferenceStorageImpl.PREF_DUMMY)).thenReturn(result)

        mStorage.getDummy()
            .test()
            .assertResult(result)

        verify(mHawkWrapper, times(1)).get<String>(SharedPreferenceStorageImpl.PREF_DUMMY)
    }

    @Test
    fun setDummy() {
        val data = "test"

        whenever(mHawkWrapper.put(SharedPreferenceStorageImpl.PREF_DUMMY, data)).thenReturn(true)

        mStorage.setDummy(data)
            .test()
            .assertResult()

        verify(mHawkWrapper, times(1)).put(SharedPreferenceStorageImpl.PREF_DUMMY, data)
    }
}