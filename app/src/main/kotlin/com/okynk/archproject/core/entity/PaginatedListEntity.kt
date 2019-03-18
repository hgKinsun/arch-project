/*
 * Copyright (c) 2018 Oky Nugroho Kusumo - Open Source Project
 */

package com.okynk.archproject.core.entity

data class PaginatedListEntity<T>(
    val data: List<T> = emptyList(),
    val results: Int = 0,
    val page: Int = 0
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as PaginatedListEntity<*>

        if (data != other.data) return false
        if (results != other.results) return false
        if (page != other.page) return false

        return true
    }

    override fun hashCode(): Int {
        var result = data.hashCode()
        result = 31 * result + results
        result = 31 * result + page
        return result
    }
}