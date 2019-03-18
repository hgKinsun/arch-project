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

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as LastUpdateDbModel

        if (key != other.key) return false
        if (timestamp != other.timestamp) return false

        return true
    }

    override fun hashCode(): Int {
        var result = key.hashCode()
        result = 31 * result + timestamp.hashCode()
        return result
    }

}