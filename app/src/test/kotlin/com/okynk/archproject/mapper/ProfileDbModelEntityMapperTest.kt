/*
 * Copyright (c) 2019 Oky Nugroho Kusumo - Open Source Project
 */

package com.okynk.archproject.mapper

import com.okynk.archproject.base.BaseTest
import com.okynk.archproject.core.entity.ProfileEntity
import com.okynk.archproject.core.mapper.Mapper
import com.okynk.archproject.core.mapper.ProfileDbModelEntityMapper
import com.okynk.archproject.core.storage.model.ProfileDbModel
import com.okynk.archproject.mockdata.mockProfileDbModel
import com.okynk.archproject.mockdata.mockProfileEntity
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class ProfileDbModelEntityMapperTest : BaseTest() {
    private lateinit var mMapper: Mapper<ProfileDbModel, ProfileEntity>

    @Before
    override fun setUp() {
        super.setUp()
        mMapper = ProfileDbModelEntityMapper()
    }

    @Test
    fun map() {
        val input = mockProfileDbModel
        val expectedResult = mockProfileEntity

        val result = mMapper.map(input)

        assertEquals(expectedResult, result)
    }
}