/*
 * Copyright (c) 2018 Oky Nugroho Kusumo - Open Source Project
 */

package com.okynk.archproject.core.mapper

import com.okynk.archproject.core.entity.ProfileEntity
import com.okynk.archproject.core.storage.model.ProfileDbModel

class ProfileDbModelEntityMapper : Mapper<ProfileDbModel, ProfileEntity> {
    override fun map(from: ProfileDbModel): ProfileEntity {
        return ProfileEntity(from.first, from.last, from.email, from.address, from.created, from.balance)
    }
}