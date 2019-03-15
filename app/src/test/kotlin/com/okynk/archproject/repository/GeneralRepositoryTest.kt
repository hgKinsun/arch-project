/*
 * Copyright (c) 2019 Oky Nugroho Kusumo - Open Source Project
 */

package com.okynk.archproject.repository

import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.never
import com.nhaarman.mockitokotlin2.times
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import com.okynk.archproject.base.BaseTest
import com.okynk.archproject.core.api.model.post.GetProfilesPostModel
import com.okynk.archproject.core.datasource.general.GeneralDataSource
import com.okynk.archproject.core.entity.PaginatedListEntity
import com.okynk.archproject.core.entity.ProfileEntity
import com.okynk.archproject.core.repository.general.GeneralRepository
import com.okynk.archproject.core.repository.general.GeneralRepositoryImpl
import io.reactivex.Completable
import io.reactivex.Observable
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations

class GeneralRepositoryTest : BaseTest() {
    private lateinit var mRepository: GeneralRepository
    @Mock private lateinit var mLocalDataSource: GeneralDataSource
    @Mock private lateinit var mRemoteDataSource: GeneralDataSource

    @Before
    override fun setUp() {
        super.setUp()
        MockitoAnnotations.initMocks(this)
        mRepository = GeneralRepositoryImpl(mLocalDataSource, mRemoteDataSource)
    }

    @Test
    fun getProfiles_fromRemote() {
        val postModel = mock<GetProfilesPostModel>()
        val remoteResult = mock<PaginatedListEntity<ProfileEntity>>()

        whenever(mLocalDataSource.getProfiles(postModel)).thenReturn(Observable.empty())
        whenever(mRemoteDataSource.getProfiles(postModel)).thenReturn(Observable.just(remoteResult))
        whenever(
            mLocalDataSource.saveProfiles(
                postModel,
                remoteResult
            )
        ).thenReturn(Completable.complete())

        mRepository.getProfiles(postModel)
            .test()
            .assertResult(remoteResult)

        verify(mLocalDataSource, times(1)).getProfiles(postModel)
        verify(mRemoteDataSource, times(1)).getProfiles(postModel)
        verify(mLocalDataSource, times(1)).saveProfiles(postModel, remoteResult)
    }

    @Test
    fun getProfiles_fromLocal() {
        val postModel = mock<GetProfilesPostModel>()
        val localResult = mock<PaginatedListEntity<ProfileEntity>>()
        val remoteResult = mock<PaginatedListEntity<ProfileEntity>>()

        whenever(mLocalDataSource.getProfiles(postModel)).thenReturn(Observable.just(localResult))
        whenever(mRemoteDataSource.getProfiles(postModel)).thenReturn(Observable.just(remoteResult))
        whenever(
            mLocalDataSource.saveProfiles(
                postModel,
                remoteResult
            )
        ).thenReturn(Completable.complete())

        mRepository.getProfiles(postModel)
            .test()
            .assertResult(localResult)

        verify(mLocalDataSource, times(1)).getProfiles(postModel)
        verify(mRemoteDataSource, times(1)).getProfiles(postModel)
        verify(mLocalDataSource, never()).saveProfiles(any(), any())
    }
}