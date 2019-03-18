/*
 * Copyright (c) 2018 Oky Nugroho Kusumo - Open Source Project
 */

package com.okynk.archproject.core.entity

data class ProfileEntity(
    val first: String = "",
    val last: String = "",
    val email: String = "",
    val address: String = "",
    val created: String = "",
    val balance: String = ""
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as ProfileEntity

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