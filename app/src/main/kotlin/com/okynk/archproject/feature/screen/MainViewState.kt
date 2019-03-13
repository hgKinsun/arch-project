/*
 * Copyright (c) 2019 Oky Nugroho Kusumo - Open Source Project
 */

package com.okynk.archproject.feature.screen

import com.okynk.archproject.core.entity.PaginatedListEntity
import com.okynk.archproject.core.entity.ProfileEntity

data class MainViewState(
    val data: PaginatedListEntity<ProfileEntity>? = null,
    val error: Throwable? = null
)