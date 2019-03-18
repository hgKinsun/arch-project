/*
 * Copyright (c) 2019 Oky Nugroho Kusumo - Open Source Project
 */

package com.okynk.archproject.datasource

import com.nhaarman.mockitokotlin2.times
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import com.okynk.archproject.base.BaseTest
import com.okynk.archproject.core.datasource.database.DatabaseDataSource
import com.okynk.archproject.core.datasource.database.DatabaseDataSourceImpl
import com.okynk.archproject.core.storage.realm.RealmStorage
import io.reactivex.Completable
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations

class DatabaseDataSourceTest : BaseTest() {
    private lateinit var mDataSource: DatabaseDataSource
    @Mock private lateinit var mStorage: RealmStorage

    @Before
    override fun setUp() {
        super.setUp()
        MockitoAnnotations.initMocks(this)

        mDataSource = DatabaseDataSourceImpl(mStorage)
    }

    @Test
    fun clear() {
        whenever(mStorage.clear()).thenReturn(Completable.complete())

        mDataSource.clear().test().assertResult()

        verify(mStorage, times(1)).clear()
    }
}