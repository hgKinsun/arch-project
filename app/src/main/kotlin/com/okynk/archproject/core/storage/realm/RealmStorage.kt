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
    fun <T : RealmObject> insertOrUpdate(data: T, lastUpdateKey: String): Completable
    fun <T : RealmObject> insert(data: T, lastUpdateKey: String): Completable
    fun <T : RealmObject> getFirst(fieldName: String = "", fieldValue: Any, lastUpdateKey: String): Observable<T>
}