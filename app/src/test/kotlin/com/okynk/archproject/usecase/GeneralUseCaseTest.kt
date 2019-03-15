/*
 * Copyright (c) 2019 Oky Nugroho Kusumo - Open Source Project
 */

package com.okynk.archproject.usecase

import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.times
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import com.okynk.archproject.base.BaseTest
import com.okynk.archproject.core.api.model.post.GetProfilesPostModel
import com.okynk.archproject.core.entity.PaginatedListEntity
import com.okynk.archproject.core.entity.ProfileEntity
import com.okynk.archproject.core.repository.general.GeneralRepository
import com.okynk.archproject.core.usecase.general.GeneralUseCase
import com.okynk.archproject.core.usecase.general.GeneralUseCaseImpl
import io.reactivex.Observable
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations

class GeneralUseCaseTest : BaseTest() {
    private lateinit var mUseCase: GeneralUseCase
    @Mock private lateinit var mRepository: GeneralRepository

    @Before
    override fun setUp() {
        super.setUp()
        MockitoAnnotations.initMocks(this)
        mUseCase = GeneralUseCaseImpl(mRepository, testScheduler)
    }

    @Test
    fun getProfiles() {
        val postModel = mock<GetProfilesPostModel>()
        val expectedResult = mock<PaginatedListEntity<ProfileEntity>>()

        whenever(mRepository.getProfiles(postModel)).thenReturn(Observable.just(expectedResult))

        mUseCase.getProfiles(postModel).test()
            .assertResult(expectedResult)

        verify(mRepository, times(1)).getProfiles(postModel)
    }

    @Test
    fun getProfile() {
        val expectedResult = mock<ProfileEntity>()

        whenever(mRepository.getProfile()).thenReturn(Observable.just(expectedResult))

        mUseCase.getProfile().test()
            .assertResult(expectedResult)

        verify(mRepository, times(1)).getProfile()
    }
}