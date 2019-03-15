/*
 * Copyright (c) 2018 Oky Nugroho Kusumo - Open Source Project
 */

package com.okynk.archproject.core.entity

data class PaginatedListEntity<T>(
    val data: List<T> = emptyList(),
    val results: Int = 0,
    val page: Int = 0
)