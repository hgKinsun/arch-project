/*
 * Copyright (c) 2019 Oky Nugroho Kusumo - Open Source Project
 */

package com.okynk.archproject.datasource

import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.times
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import com.okynk.archproject.BuildConfig
import com.okynk.archproject.base.BaseTest
import com.okynk.archproject.core.api.ApiService
import com.okynk.archproject.core.api.model.post.GetProfilesPostModel
import com.okynk.archproject.core.api.model.response.ListWrapperResponse
import com.okynk.archproject.core.api.model.response.ProfileResponse
import com.okynk.archproject.core.datasource.general.GeneralDataSource
import com.okynk.archproject.core.datasource.general.GeneralRemoteDataSource
import com.okynk.archproject.core.entity.PaginatedListEntity
import com.okynk.archproject.core.entity.ProfileEntity
import com.okynk.archproject.core.mapper.Mapper
import com.okynk.archproject.mockdata.mockGetProfilesPostModel
import com.okynk.archproject.mockdata.mockListWrapperProfileResponse
import com.okynk.archproject.mockdata.mockPaginatedListProfileEntity
import com.okynk.archproject.mockdata.mockProfileEntity
import io.reactivex.Observable
import org.junit.Before
import org.junit.Test
import org.koin.standalone.inject
import org.mockito.Mock
import org.mockito.MockitoAnnotations

class GeneralRemoteDataSourceTest : BaseTest() {
    private lateinit var mDataSource: GeneralDataSource
    private val mMapperList: Mapper<ListWrapperResponse<ProfileResponse>, PaginatedListEntity<ProfileEntity>> by inject(
        Mapper.PROFILE_LIST_RESPONSE_TO_ENTITY
    )
    private val mMapperItem: Mapper<ProfileResponse, ProfileEntity> by inject(Mapper.PROFILE_RESPONSE_TO_ENTITY)
    @Mock private lateinit var mApiService: ApiService

    @Before
    override fun setUp() {
        super.setUp()
        MockitoAnnotations.initMocks(this)
        mDataSource = GeneralRemoteDataSource(mApiService, mMapperList, mMapperItem)
    }

    @Test
    fun getProfiles() {
        val postModel = mockGetProfilesPostModel
        val response = mockListWrapperProfileResponse
        val expectedResult = mockPaginatedListProfileEntity

        whenever(
            mApiService.getProfiles(
                BuildConfig.API_KEY,
                postModel.results,
                postModel.page
            )
        ).thenReturn(
            Observable.just(response)
        )

        mDataSource.getProfiles(postModel)
            .test()
            .assertResult(expectedResult)

        verify(mApiService, times(1)).getProfiles(
            BuildConfig.API_KEY,
            postModel.results,
            postModel.page
        )
    }

    @Test(expected = Exception::class)
    fun saveProfiles() {
        val postModel = mock<GetProfilesPostModel>()
        val paginatedListEntity = mock<PaginatedListEntity<ProfileEntity>>()

        mDataSource.saveProfiles(postModel, paginatedListEntity)
    }

    @Test
    fun getProfile() {
        val response = mockListWrapperProfileResponse
        val expectedResult = mockProfileEntity

        whenever(mApiService.getProfiles(BuildConfig.API_KEY, 1, 1)).thenReturn(
            Observable.just(
                response
            )
        )

        mDataSource.getProfile().test().assertResult(expectedResult)

        verify(mApiService, times(1)).getProfiles(BuildConfig.API_KEY, 1, 1)
    }

    @Test(expected = Exception::class)
    fun saveProfile() {
        val data = mock<ProfileEntity>()

        mDataSource.saveProfile(data)
    }
}