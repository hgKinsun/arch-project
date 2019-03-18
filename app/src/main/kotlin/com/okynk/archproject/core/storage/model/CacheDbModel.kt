/*
 * Copyright (c) 2019 Oky Nugroho Kusumo - Open Source Project
 */

package com.okynk.archproject.core.storage.model

import io.realm.RealmObject

open class CacheDbModel(
    var apiUrl: String = "",
    var postModel: String = "",
    var data: String = ""
) : RealmObject() {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as CacheDbModel

        if (apiUrl != other.apiUrl) return false
        if (postModel != other.postModel) return false
        if (data != other.data) return false

        return true
    }

    override fun hashCode(): Int {
        var result = apiUrl.hashCode()
        result = 31 * result + postModel.hashCode()
        result = 31 * result + data.hashCode()
        return result
    }
}