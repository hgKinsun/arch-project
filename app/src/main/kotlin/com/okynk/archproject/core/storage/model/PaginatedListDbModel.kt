/*
 * Copyright (c) 2018 Oky Nugroho Kusumo - Open Source Project
 */

package com.okynk.archproject.core.storage.model

import io.realm.RealmList
import io.realm.RealmObject

open class PaginatedListDbModel<T>(
    val data: RealmList<T> = RealmList(),
    val results: Int = 0,
    val page: Int = 0
) : RealmObject() {
    companion object {
        const val QUERY_PAGE = "page"
    }
}