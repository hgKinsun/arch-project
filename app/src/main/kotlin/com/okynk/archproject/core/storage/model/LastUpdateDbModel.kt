/*
 * Copyright (c) 2018 Oky Nugroho Kusumo - Open Source Project
 */

package com.okynk.archproject.core.storage.model

import io.realm.RealmObject
import io.realm.annotations.PrimaryKey

open class LastUpdateDbModel(
    @PrimaryKey var key: String = "",
    var timestamp: Long = 0
) : RealmObject() {
    companion object {
        const val QUERY_KEY = "key"

        const val PROFILE = "profile"
        const val PROFILE_LIST = "profile_list"
    }
}