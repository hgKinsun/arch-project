/*
 * Copyright (c) 2018 Oky Nugroho Kusumo - Open Source Project
 */

package com.okynk.archproject.core.storage.realm

import io.reactivex.Completable
import io.reactivex.Observable
import io.realm.RealmObject

interface RealmStorage {
    fun touchLastUpdate(key: String): Completable
    fun isLastUpdateExpired(key: String): Observable<Boolean>
    fun invalidateLastUpdate(key: String): Completable
    fun clear(): Completable
    fun <T : RealmObject> insertOrUpdate(data: T): Completable
    fun <T : RealmObject> insert(data: T): Completable
    fun <T : RealmObject> getFirst(clazz: Class<out RealmObject>): Observable<T>
    fun <T : RealmObject> getAll(clazz: Class<out RealmObject>): Observable<List<T>>
}