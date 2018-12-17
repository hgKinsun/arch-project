/*
 * Copyright (c) 2018 Oky Nugroho Kusumo - Open Source Project
 */

package com.okynk.archproject.core.mapper

import com.okynk.archproject.core.entity.PaginatedListEntity
import com.okynk.archproject.core.entity.ProfileEntity
import com.okynk.archproject.core.storage.model.PaginatedListDbModel
import com.okynk.archproject.core.storage.model.ProfileDbModel
import io.realm.RealmList

class ProfileListEntityDbModelMapper(val itemMapper: Mapper<ProfileEntity, ProfileDbModel>) :
    Mapper<PaginatedListEntity<ProfileEntity>, PaginatedListDbModel<ProfileDbModel>> {
    override fun map(from: PaginatedListEntity<ProfileEntity>): PaginatedListDbModel<ProfileDbModel> {
        val list = RealmList<ProfileDbModel>()
        from.data.forEach {
            list.add(itemMapper.map(it))
        }
        return PaginatedListDbModel(list, from.results, from.page)
    }
}