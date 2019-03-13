/*
 * Copyright (c) 2018 Oky Nugroho Kusumo - Open Source Project
 */

package com.okynk.archproject.core.mapper

import com.okynk.archproject.core.entity.ProfileEntity
import com.okynk.archproject.core.storage.model.ProfileDbModel

class ProfileEntityDbModelMapper : Mapper<ProfileEntity, ProfileDbModel> {
    override fun map(from: ProfileEntity): ProfileDbModel {
        return ProfileDbModel(
            from.first,
            from.last,
            from.email,
            from.address,
            from.created,
            from.balance
        )
    }
}