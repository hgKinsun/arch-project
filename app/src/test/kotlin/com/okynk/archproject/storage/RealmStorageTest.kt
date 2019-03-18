/*
 * Copyright (c) 2019 Oky Nugroho Kusumo - Open Source Project
 */

package com.okynk.archproject.storage

import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.times
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import com.okynk.archproject.base.BaseTest
import com.okynk.archproject.core.storage.model.LastUpdateDbModel
import com.okynk.archproject.core.storage.realm.RealmStorage
import com.okynk.archproject.core.storage.realm.RealmStorageImpl
import com.okynk.archproject.core.util.generalutil.GeneralUtil
import com.okynk.archproject.core.wrapper.realm.RealmWrapper
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations

class RealmStorageTest : BaseTest() {
    private lateinit var mStorage: RealmStorage
    @Mock private lateinit var mRealmWrapper: RealmWrapper
    @Mock private lateinit var mGeneralUtil: GeneralUtil

    @Before
    override fun setUp() {
        super.setUp()
        MockitoAnnotations.initMocks(this)
        mStorage = RealmStorageImpl(mRealmWrapper, mGeneralUtil)
    }

    @Test
    fun touchLastUpdate() {
        val timestamp = 1552897926L
        val key = LastUpdateDbModel.PROFILE
        val lastUpdate = LastUpdateDbModel(
            key,
            timestamp
        )

        whenever(mGeneralUtil.getCurrentTimestamp()).thenReturn(timestamp)

        mStorage.touchLastUpdate(key).test().assertResult()

        verify(mGeneralUtil, times(1)).getCurrentTimestamp()
        verify(mRealmWrapper, times(1)).insertOrUpdate(lastUpdate)
    }

    @Test
    fun touchLastUpdate_withParam() {
        val timestamp = 1552897926L
        val key = LastUpdateDbModel.PROFILE
        val params = "params"
        val lastUpdate = LastUpdateDbModel(
            "$key $params",
            timestamp
        )

        whenever(mGeneralUtil.getCurrentTimestamp()).thenReturn(timestamp)

        mStorage.touchLastUpdate(key, params)
            .test()
            .assertResult()

        verify(mGeneralUtil, times(1)).getCurrentTimestamp()
        verify(mRealmWrapper, times(1)).insertOrUpdate(lastUpdate)
    }

    @Test
    fun isLastUpdateExpired() {
        val currentTimestamp = 1552897926L
        val dataTimestamp = 1452897926L
        val key = LastUpdateDbModel.PROFILE
        val lastUpdate = LastUpdateDbModel(
            key,
            dataTimestamp
        )
        val conditions = HashMap<String, String>()
        conditions[LastUpdateDbModel.QUERY_KEY] = key

        whenever(mGeneralUtil.getCurrentTimestamp()).thenReturn(currentTimestamp)
        whenever(
            mRealmWrapper.getFirst<LastUpdateDbModel>(
                LastUpdateDbModel::class.java,
                conditions
            )
        ).thenReturn(lastUpdate)

        mStorage.isLastUpdateExpired(key)
            .test()
            .assertResult(true)

        verify(mGeneralUtil, times(1)).getCurrentTimestamp()
        verify(mRealmWrapper, times(1)).getFirst<LastUpdateDbModel>(
            LastUpdateDbModel::class.java,
            conditions
        )
    }

    @Test
    fun clear() {
        mStorage.clear().test().assertResult()

        verify(mRealmWrapper, times(1)).deleteAll()
    }

    @Test
    fun insertOrUpdate() {
        val data = mock<LastUpdateDbModel>()
        mStorage.insertOrUpdate(data).test().assertResult()

        verify(mRealmWrapper, times(1)).insertOrUpdate(data)
    }

    @Test
    fun insert() {
        val data = mock<LastUpdateDbModel>()
        mStorage.insert(data).test().assertResult()

        verify(mRealmWrapper, times(1)).insert(data)
    }

    @Test
    fun getFirst() {
        val clazz = LastUpdateDbModel::class.java
        val conditions = HashMap<String, String>()
        val result = mock<LastUpdateDbModel>()

        whenever(mRealmWrapper.getFirst<LastUpdateDbModel>(clazz, conditions)).thenReturn(result)

        mStorage.getFirst<LastUpdateDbModel>(clazz, conditions).test().assertResult(result)

        verify(mRealmWrapper, times(1)).getFirst<LastUpdateDbModel>(clazz, conditions)
    }

    @Test
    fun getAll() {
        val clazz = LastUpdateDbModel::class.java
        val conditions = HashMap<String, String>()
        val result = mock<List<LastUpdateDbModel>>()

        whenever(mRealmWrapper.getAll<LastUpdateDbModel>(clazz, conditions)).thenReturn(result)

        mStorage.getAll<LastUpdateDbModel>(clazz, conditions).test().assertResult(result)

        verify(mRealmWrapper, times(1)).getAll<LastUpdateDbModel>(clazz, conditions)
    }
}