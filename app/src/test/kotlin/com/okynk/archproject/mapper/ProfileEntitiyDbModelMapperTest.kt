/*
 * Copyright (c) 2019 Oky Nugroho Kusumo - Open Source Project
 */

package com.okynk.archproject.mapper

import com.okynk.archproject.base.BaseTest
import com.okynk.archproject.core.api.model.response.ListWrapperResponse
import com.okynk.archproject.core.api.model.response.ProfileResponse
import com.okynk.archproject.core.entity.PaginatedListEntity
import com.okynk.archproject.core.entity.ProfileEntity
import com.okynk.archproject.core.mapper.Mapper
import com.okynk.archproject.core.mapper.ProfileListResponseEntityMapper
import com.okynk.archproject.mockdata.mockListWrapperProfileResponse
import com.okynk.archproject.mockdata.mockPaginatedListProfileEntity
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.koin.standalone.inject

class ProfileEntitiyDbModelMapperTest : BaseTest() {
    private lateinit var mMapper: Mapper<ListWrapperResponse<ProfileResponse>, PaginatedListEntity<ProfileEntity>>
    private val mProfileMapper: Mapper<ProfileResponse, ProfileEntity> by inject(Mapper.PROFILE_RESPONSE_TO_ENTITY)

    @Before
    override fun setUp() {
        super.setUp()
        mMapper = ProfileListResponseEntityMapper(mProfileMapper)
    }

    @Test
    fun map() {
        val input = mockListWrapperProfileResponse
        val expectedResult = mockPaginatedListProfileEntity

        val result = mMapper.map(input)

        assertEquals(expectedResult, result)
    }
}