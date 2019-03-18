/*
 * Copyright (c) 2018 Oky Nugroho Kusumo - Open Source Project
 */

package com.okynk.archproject.core.storage.model

import io.realm.RealmObject

open class ProfileDbModel(
    var first: String = "",
    var last: String = "",
    var email: String = "",
    var address: String = "",
    var created: String = "",
    var balance: String = ""
) : RealmObject() {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as ProfileDbModel

        if (first != other.first) return false
        if (last != other.last) return false
        if (email != other.email) return false
        if (address != other.address) return false
        if (created != other.created) return false
        if (balance != other.balance) return false

        return true
    }

    override fun hashCode(): Int {
        var result = first.hashCode()
        result = 31 * result + last.hashCode()
        result = 31 * result + email.hashCode()
        result = 31 * result + address.hashCode()
        result = 31 * result + created.hashCode()
        result = 31 * result + balance.hashCode()
        return result
    }
}
