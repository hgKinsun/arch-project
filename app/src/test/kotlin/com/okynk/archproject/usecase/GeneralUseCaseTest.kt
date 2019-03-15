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
import io.reactivex.Observable
import org.junit.Before
import org.junit.Test
import org.koin.standalone.inject
import org.koin.test.declareMock

class GeneralUseCaseTest : BaseTest() {
    private val mUseCase: GeneralUseCase by inject()
    private val mRepository: GeneralRepository by inject()

    @Before
    override fun setUp() {
        super.setUp()
        declareMock<GeneralRepository>()
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