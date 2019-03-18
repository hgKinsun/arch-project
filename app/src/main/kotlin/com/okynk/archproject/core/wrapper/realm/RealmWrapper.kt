/*
 * Copyright (c) 2019 Oky Nugroho Kusumo - Open Source Project
 */

package com.okynk.archproject.core.wrapper.realm

import io.realm.RealmObject

interface RealmWrapper {
    fun <T : RealmObject> insertOrUpdate(data: T)
    fun <T : RealmObject> insert(data: T)
    fun <T : RealmObject> getFirst(
        clazz: Class<out RealmObject>,
        equalConditions: Map<String, String>? = null
    ): T?

    fun <T : RealmObject> getAll(
        clazz: Class<out RealmObject>,
        equalConditions: Map<String, String>? = null
    ): List<T>

    fun deleteAll()
}