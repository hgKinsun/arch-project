/*
 * Copyright (c) 2019 Oky Nugroho Kusumo - Open Source Project
 */

package com.okynk.archproject.repository

import com.nhaarman.mockitokotlin2.times
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import com.okynk.archproject.base.BaseTest
import com.okynk.archproject.core.datasource.database.DatabaseDataSource
import com.okynk.archproject.core.repository.database.DatabaseRepository
import com.okynk.archproject.core.repository.database.DatabaseRepositoryImpl
import io.reactivex.Completable
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations

class DatabaseRepositoryTest : BaseTest() {
    private lateinit var mRepository: DatabaseRepository
    @Mock private lateinit var mDataSource: DatabaseDataSource

    @Before
    override fun setUp() {
        super.setUp()
        MockitoAnnotations.initMocks(this)
        mRepository = DatabaseRepositoryImpl(mDataSource)
    }

    @Test
    fun clear() {
        whenever(mDataSource.clear()).thenReturn(Completable.complete())

        mRepository.clear().test()
            .assertResult()

        verify(mDataSource, times(1)).clear()
    }
}