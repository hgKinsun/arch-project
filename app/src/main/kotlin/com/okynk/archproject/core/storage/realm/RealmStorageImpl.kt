/*
 * Copyright (c) 2018 Oky Nugroho Kusumo - Open Source Project
 */

package com.okynk.archproject.core.storage.realm

import com.okynk.archproject.core.storage.model.LastUpdateDbModel
import com.okynk.archproject.core.util.getCurrentTimestamp
import com.okynk.archproject.util.Constants
import io.reactivex.Completable
import io.reactivex.Observable
import io.realm.Realm
import io.realm.RealmObject
import io.realm.kotlin.where

class RealmStorageImpl : RealmStorage {
    override fun touchLastUpdate(key: String): Completable {
        return Completable.create { e ->
            val realm = Realm.getDefaultInstance()
            val lastUpdate = LastUpdateDbModel(key, getCurrentTimestamp())
            realm.executeTransaction { r ->
                r.insertOrUpdate(lastUpdate)
            }
            realm.close()
            e.onComplete()
        }
    }

    override fun isLastUpdateExpired(key: String): Observable<Boolean> {
        return Observable.create { emitter ->
            val realm = Realm.getDefaultInstance()
            val lastUpdate = realm.where<LastUpdateDbModel>().equalTo(LastUpdateDbModel.QUERY_KEY, key).findFirst()
            lastUpdate?.let {
                emitter.onNext((getCurrentTimestamp() - it.timestamp > getLastUpdateLifetime(key)))
            } ?: run {
                emitter.onNext(true)
            }
            realm.close()
            emitter.onComplete()
        }
    }

    override fun invalidateLastUpdate(key: String): Completable {
        return Completable.create { emitter ->
            val realm = Realm.getDefaultInstance()
            val lastUpdate = realm.where<LastUpdateDbModel>().equalTo(LastUpdateDbModel.QUERY_KEY, key).findFirst()
            lastUpdate?.let {
                realm.executeTransaction { r ->
                    it.deleteFromRealm()
                }
            }
            realm.close()
            emitter.onComplete()
        }
    }

    override fun clear(): Completable {
        return Completable.create { emitter ->
            val realm = Realm.getDefaultInstance()
            realm.executeTransaction {
                it.deleteAll()
            }
            realm.close()
            emitter.onComplete()
        }
    }

    override fun <T : RealmObject> insertOrUpdate(data: T, lastUpdateKey: String): Completable {
        return Completable.create { emitter ->
            val realm = Realm.getDefaultInstance()
            val lastUpdate = LastUpdateDbModel(lastUpdateKey, getCurrentTimestamp())
            realm.executeTransaction {
                it.insertOrUpdate(data)
                it.insertOrUpdate(lastUpdate)
            }
            realm.close()
            emitter.onComplete()
        }
    }

    override fun <T : RealmObject> insert(data: T, lastUpdateKey: String): Completable {
        return Completable.create { emitter ->
            val realm = Realm.getDefaultInstance()
            val lastUpdate = LastUpdateDbModel(lastUpdateKey, getCurrentTimestamp())
            realm.executeTransaction {
                it.insert(data)
                it.insertOrUpdate(lastUpdate)
            }
            realm.close()
            emitter.onComplete()
        }
    }

    private fun getLastUpdateLifetime(key: String): Long {
        when (key) {
            LastUpdateDbModel.PROFILE -> return Constants.ONE_DAY_IN_SECOND
            else -> {
                throw Exception(Constants.EXCEPTION_NOT_DEFINED_LASTUPDATELIFETIME)
            }
        }
    }
}