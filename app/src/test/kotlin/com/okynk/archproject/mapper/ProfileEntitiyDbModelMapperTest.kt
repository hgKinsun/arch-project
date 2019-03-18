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
import com.okynk.archproject.core.mapper.ProfileResponseEntityMapper
import com.okynk.archproject.mockdata.mockListWrapperProfileResponse
import com.okynk.archproject.mockdata.mockPaginatedListProfileEntity
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.mockito.MockitoAnnotations

class ProfileEntitiyDbModelMapperTest : BaseTest() {
    private lateinit var mMapper: Mapper<ListWrapperResponse<ProfileResponse>, PaginatedListEntity<ProfileEntity>>
    private lateinit var mProfileMapper: Mapper<ProfileResponse, ProfileEntity>

    @Before
    override fun setUp() {
        super.setUp()
        MockitoAnnotations.initMocks(this)

        // can't mock this because we need to run the "map logic"
        mProfileMapper = ProfileResponseEntityMapper()
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