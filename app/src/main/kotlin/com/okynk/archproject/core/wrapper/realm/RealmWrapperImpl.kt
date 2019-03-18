/*
 * Copyright (c) 2019 Oky Nugroho Kusumo - Open Source Project
 */

package com.okynk.archproject.core.wrapper.realm

import io.realm.Realm
import io.realm.RealmObject

class RealmWrapperImpl : RealmWrapper {
    override fun <T : RealmObject> insertOrUpdate(data: T) {
        val realm = Realm.getDefaultInstance()
        realm.executeTransaction {
            it.insertOrUpdate(data)
        }
        realm.close()
    }

    override fun <T : RealmObject> insert(data: T) {
        val realm = Realm.getDefaultInstance()
        realm.executeTransaction {
            it.insert(data)
        }
        realm.close()
    }

    override fun <T : RealmObject> getFirst(
        clazz: Class<out RealmObject>,
        equalConditions: Map<String, String>?
    ): T? {
        val realm = Realm.getDefaultInstance()
        var query = realm.where(clazz)

        equalConditions?.let {
            for ((k, v) in it) {
                query = query.equalTo(k, v)
            }
        }

        var result = query.findFirst()
        result?.let {
            result = realm.copyFromRealm(it)
        }
        realm.close()

        return if (result != null) result as T else null
    }

    override fun <T : RealmObject> getAll(
        clazz: Class<out RealmObject>,
        equalConditions: Map<String, String>?
    ): List<T> {
        val realm = Realm.getDefaultInstance()
        var query = realm.where(clazz)

        equalConditions?.let {
            for ((k, v) in it) {
                query = query.equalTo(k, v)
            }
        }

        val result = realm.copyFromRealm(query.findAll())

        realm.close()

        return result as List<T>
    }

    override fun deleteAll() {
        val realm = Realm.getDefaultInstance()
        realm.executeTransaction {
            it.deleteAll()
        }
        realm.close()
    }
}