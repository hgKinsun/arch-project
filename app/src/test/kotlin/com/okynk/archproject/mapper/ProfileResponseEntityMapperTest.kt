/*
 * Copyright (c) 2019 Oky Nugroho Kusumo - Open Source Project
 */

package com.okynk.archproject.mapper

import com.okynk.archproject.base.BaseTest
import com.okynk.archproject.core.api.model.response.ProfileResponse
import com.okynk.archproject.core.entity.ProfileEntity
import com.okynk.archproject.core.mapper.Mapper
import com.okynk.archproject.core.mapper.ProfileResponseEntityMapper
import com.okynk.archproject.mockdata.mockProfileEntity
import com.okynk.archproject.mockdata.mockProfileResponse
import junit.framework.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class ProfileResponseEntityMapperTest : BaseTest() {
    private lateinit var mMapper: Mapper<ProfileResponse, ProfileEntity>

    @Before
    override fun setUp() {
        super.setUp()
        mMapper = ProfileResponseEntityMapper()
    }

    @Test
    fun map() {
        val input = mockProfileResponse
        val expectedResult = mockProfileEntity

        val result = mMapper.map(input)

        assertEquals(expectedResult, result)
    }
}