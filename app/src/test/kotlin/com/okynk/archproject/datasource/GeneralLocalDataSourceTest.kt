/*
 * Copyright (c) 2019 Oky Nugroho Kusumo - Open Source Project
 */

package com.okynk.archproject.datasource

import com.google.gson.Gson
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.times
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import com.okynk.archproject.base.BaseTest
import com.okynk.archproject.core.api.ApiService
import com.okynk.archproject.core.api.model.post.GetProfilesPostModel
import com.okynk.archproject.core.datasource.general.GeneralDataSource
import com.okynk.archproject.core.datasource.general.GeneralLocalDataSource
import com.okynk.archproject.core.entity.ProfileEntity
import com.okynk.archproject.core.mapper.Mapper
import com.okynk.archproject.core.storage.model.CacheDbModel
import com.okynk.archproject.core.storage.model.LastUpdateDbModel
import com.okynk.archproject.core.storage.model.ProfileDbModel
import com.okynk.archproject.core.storage.realm.RealmStorage
import com.okynk.archproject.mockdata.mockGetProfilesPostModel
import com.okynk.archproject.mockdata.mockPaginatedListProfileEntity
import com.okynk.archproject.mockdata.mockProfileDbModel
import com.okynk.archproject.mockdata.mockProfileEntity
import io.reactivex.Completable
import io.reactivex.Observable
import org.junit.Before
import org.junit.Test
import org.koin.standalone.inject
import org.mockito.Mock
import org.mockito.MockitoAnnotations

class GeneralLocalDataSourceTest : BaseTest() {
    private lateinit var mDataSource: GeneralDataSource
    private val mGson: Gson by inject()
    private val mProfileEntityDbMapper: Mapper<ProfileEntity, ProfileDbModel> by inject(Mapper.PROFILE_ENTITY_TO_DB)
    private val mProfileDbEntityMapper: Mapper<ProfileDbModel, ProfileEntity> by inject(Mapper.PROFILE_DB_TO_ENTITY)
    @Mock private lateinit var mRealmStorage: RealmStorage

    @Before
    override fun setUp() {
        super.setUp()
        MockitoAnnotations.initMocks(this)

        mDataSource = GeneralLocalDataSource(
            mRealmStorage,
            mGson,
            mProfileEntityDbMapper,
            mProfileDbEntityMapper
        )
    }

    @Test
    fun getProfiles_LastUpdateExpired() {
        val postModel = mock<GetProfilesPostModel>()

        whenever(
            mRealmStorage.isLastUpdateExpired(
                LastUpdateDbModel.PROFILE_LIST,
                postModel.toString()
            )
        ).thenReturn(
            Observable.just(true)
        )

        mDataSource.getProfiles(postModel).test()
            .assertSubscribed()
            .assertComplete()
            .assertNoValues()
            .assertNoErrors()

        verify(mRealmStorage, times(1)).isLastUpdateExpired(
            LastUpdateDbModel.PROFILE_LIST,
            postModel.toString()
        )
    }

    @Test
    fun getProfiles_LastUpdateNotExpired() {
        val postModel = mockGetProfilesPostModel
        val expectedResult = mockPaginatedListProfileEntity
        val cacheDbModel = CacheDbModel(
            ApiService.PROFILES_URL,
            postModel.toString(),
            mGson.toJson(expectedResult)
        )

        whenever(
            mRealmStorage.isLastUpdateExpired(
                LastUpdateDbModel.PROFILE_LIST,
                postModel.toString()
            )
        ).thenReturn(
            Observable.just(false)
        )
        whenever(mRealmStorage.getFirst<CacheDbModel>(CacheDbModel::class.java)).thenReturn(
            Observable.just(cacheDbModel)
        )

        mDataSource.getProfiles(postModel).test()
            .assertResult(expectedResult)

        verify(mRealmStorage, times(1)).isLastUpdateExpired(
            LastUpdateDbModel.PROFILE_LIST,
            postModel.toString()
        )
        verify(mRealmStorage, times(1)).getFirst<CacheDbModel>(CacheDbModel::class.java)
    }

    @Test
    fun saveProfiles() {
        val postModel = mockGetProfilesPostModel
        val data = mockPaginatedListProfileEntity
        val cache = CacheDbModel(
            ApiService.PROFILES_URL,
            postModel.toString(),
            mGson.toJson(data)
        )

        whenever(mRealmStorage.insert(cache)).thenReturn(Completable.complete())
        whenever(
            mRealmStorage.touchLastUpdate(
                LastUpdateDbModel.PROFILE_LIST,
                postModel.toString()
            )
        ).thenReturn(
            Completable.complete()
        )

        mDataSource.saveProfiles(postModel, data)
            .test()
            .assertResult()

        verify(mRealmStorage, times(1)).insert(cache)
        verify(mRealmStorage, times(1)).touchLastUpdate(
            LastUpdateDbModel.PROFILE_LIST,
            postModel.toString()
        )
    }

    @Test
    fun getProfile_LastUpdateExpired() {
        whenever(mRealmStorage.isLastUpdateExpired(LastUpdateDbModel.PROFILE)).thenReturn(
            Observable.just(
                true
            )
        )

        mDataSource.getProfile().test()
            .assertSubscribed()
            .assertComplete()
            .assertNoValues()
            .assertNoErrors()

        verify(mRealmStorage, times(1)).isLastUpdateExpired(LastUpdateDbModel.PROFILE)
    }

    @Test
    fun getProfile_LastUdpateNotExpired() {
        val expectedResult = mockProfileEntity
        val profileDbModel = mockProfileDbModel

        whenever(mRealmStorage.isLastUpdateExpired(LastUpdateDbModel.PROFILE)).thenReturn(
            Observable.just(
                false
            )
        )
        whenever(mRealmStorage.getFirst<ProfileDbModel>(ProfileDbModel::class.java)).thenReturn(
            Observable.just(profileDbModel)
        )

        mDataSource.getProfile().test().assertResult(expectedResult)

        verify(mRealmStorage, times(1)).isLastUpdateExpired(LastUpdateDbModel.PROFILE)
        verify(mRealmStorage, times(1)).getFirst<ProfileDbModel>(ProfileDbModel::class.java)
    }

    @Test
    fun saveProfile() {
        val data = mockProfileEntity
        val dbModel = mockProfileDbModel

        whenever(mRealmStorage.insert(dbModel)).thenReturn(Completable.complete())
        whenever(mRealmStorage.touchLastUpdate(LastUpdateDbModel.PROFILE)).thenReturn(Completable.complete())

        mDataSource.saveProfile(data).test().assertResult()

        verify(mRealmStorage, times(1)).insert(dbModel)
        verify(mRealmStorage, times(1)).touchLastUpdate(LastUpdateDbModel.PROFILE)
    }
}