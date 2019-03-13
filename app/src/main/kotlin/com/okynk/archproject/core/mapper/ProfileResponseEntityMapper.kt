/*
 * Copyright (c) 2018 Oky Nugroho Kusumo - Open Source Project
 */

package com.okynk.archproject.core.mapper

import com.okynk.archproject.core.api.model.response.ProfileResponse
import com.okynk.archproject.core.entity.ProfileEntity

class ProfileResponseEntityMapper : Mapper<ProfileResponse, ProfileEntity> {
    override fun map(from: ProfileResponse): ProfileEntity {
        return ProfileEntity(
            from.first,
            from.last,
            from.email,
            from.address,
            from.created,
            from.balance
        )
    }
}