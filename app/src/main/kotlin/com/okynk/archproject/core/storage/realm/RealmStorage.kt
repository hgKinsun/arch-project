/*
 * Copyright (c) 2018 Oky Nugroho Kusumo - Open Source Project
 */

package com.okynk.archproject.core.storage.realm

import io.reactivex.Completable
import io.reactivex.Observable
import io.realm.RealmObject

interface RealmStorage {
    fun touchLastUpdate(key: String, params: String? = null): Completable
    fun isLastUpdateExpired(key: String, params: String? = null): Observable<Boolean>
    fun invalidateLastUpdate(key: String, params: String? = null): Completable
    fun clear(): Completable
    fun <T : RealmObject> insertOrUpdate(data: T): Completable
    fun <T : RealmObject> insert(data: T): Completable
    fun <T : RealmObject> getFirst(
        clazz: Class<out RealmObject>,
        equalConditions: Map<String, String>? = null
    ): Observable<T>

    fun <T : RealmObject> getAll(
        clazz: Class<out RealmObject>,
        equalConditions: Map<String, String>? = null
    ): Observable<List<T>>
}