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
import org.junit.Test
import org.koin.standalone.get
import org.koin.standalone.inject
import org.koin.test.declareMock

class GeneralUseCaseTest : BaseTest() {
    private val mGeneralUseCase: GeneralUseCase by inject()

    @Test
    fun getProfiles() {
        declareMock<GeneralRepository>()
        val postModel = mock<GetProfilesPostModel>()
        val expectedResult = mock<PaginatedListEntity<ProfileEntity>>()
        val generalRepository = get<GeneralRepository>()

        whenever(generalRepository.getProfiles(postModel)).thenReturn(Observable.just(expectedResult))

        mGeneralUseCase.getProfiles(postModel).test()
            .assertSubscribed()
            .assertResult(expectedResult)

        verify(generalRepository, times(1)).getProfiles(postModel)
    }

    @Test
    fun getProfile() {
        declareMock<GeneralRepository>()
        val generalRepository = get<GeneralRepository>()
        val expectedResult = mock<ProfileEntity>()

        whenever(generalRepository.getProfile()).thenReturn(Observable.just(expectedResult))

        mGeneralUseCase.getProfile().test()
            .assertSubscribed()
            .assertResult(expectedResult)

        verify(generalRepository, times(1)).getProfile()
    }
}