/*
 * Copyright (c) 2018 Oky Nugroho Kusumo - Open Source Project
 */

package com.okynk.archproject.core.mapper

import com.okynk.archproject.core.entity.PaginatedListEntity
import com.okynk.archproject.core.entity.ProfileEntity
import com.okynk.archproject.core.storage.model.PaginatedListDbModel
import com.okynk.archproject.core.storage.model.ProfileDbModel

class ProfileListDbModelEntityMapper(val itemMapper: Mapper<ProfileDbModel, ProfileEntity>) :
    Mapper<PaginatedListDbModel<ProfileDbModel>, PaginatedListEntity<ProfileEntity>> {
    override fun map(from: PaginatedListDbModel<ProfileDbModel>): PaginatedListEntity<ProfileEntity> {
        val list = from.data.map { itemMapper.map(it) }
        return PaginatedListEntity(list, from.results, from.page)
    }
}