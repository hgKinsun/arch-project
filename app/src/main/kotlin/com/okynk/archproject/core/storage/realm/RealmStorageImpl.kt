/*
 * Copyright (c) 2018 Oky Nugroho Kusumo - Open Source Project
 */

package com.okynk.archproject.core.storage.realm

import com.okynk.archproject.core.storage.model.LastUpdateDbModel
import com.okynk.archproject.core.util.CoreConstants
import com.okynk.archproject.core.util.getCurrentTimestamp
import io.reactivex.Completable
import io.reactivex.Observable
import io.realm.Realm
import io.realm.RealmObject
import io.realm.kotlin.where

class RealmStorageImpl : RealmStorage {
    override fun touchLastUpdate(key: String, params: String?): Completable {
        return Completable.fromCallable {
            val realm = Realm.getDefaultInstance()
            val lastUpdate = LastUpdateDbModel(getLastUpdateKey(key, params), getCurrentTimestamp())
            realm.executeTransaction { r ->
                r.insertOrUpdate(lastUpdate)
            }
            realm.close()
        }
    }

    override fun isLastUpdateExpired(key: String, params: String?): Observable<Boolean> {
        return Observable.fromCallable {
            val realm = Realm.getDefaultInstance()
            val lastUpdate =
                realm.where<LastUpdateDbModel>()
                    .equalTo(LastUpdateDbModel.QUERY_KEY, getLastUpdateKey(key, params))
                    .findFirst()
            var expired = true
            lastUpdate?.let {
                expired = (getCurrentTimestamp() - it.timestamp > getLastUpdateLifetime(key))
            }

            realm.close()

            return@fromCallable expired
        }
    }

    override fun invalidateLastUpdate(key: String, params: String?): Completable {
        return Completable.fromCallable {
            val realm = Realm.getDefaultInstance()
            val lastUpdate =
                realm.where<LastUpdateDbModel>().contains(LastUpdateDbModel.QUERY_KEY, key)
                    .findFirst()
            lastUpdate?.let {
                realm.executeTransaction { r ->
                    it.deleteFromRealm()
                }
            }
            realm.close()
        }
    }

    override fun clear(): Completable {
        return Completable.fromCallable {
            val realm = Realm.getDefaultInstance()
            realm.executeTransaction {
                it.deleteAll()
            }
            realm.close()
        }
    }

    override fun <T : RealmObject> insertOrUpdate(data: T): Completable {
        return Completable.fromCallable {
            val realm = Realm.getDefaultInstance()
            realm.executeTransaction {
                it.insertOrUpdate(data)
            }
            realm.close()
        }
    }

    override fun <T : RealmObject> insert(data: T): Completable {
        return Completable.fromCallable {
            val realm = Realm.getDefaultInstance()
            realm.executeTransaction {
                it.insert(data)
            }
            realm.close()
        }
    }

    override fun <T : RealmObject> getFirst(
        clazz: Class<out RealmObject>,
        equalConditions: Map<String, String>?
    ): Observable<T> {
        return Observable.fromCallable {
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

            return@fromCallable result as T
        }
    }

    override fun <T : RealmObject> getAll(
        clazz: Class<out RealmObject>,
        equalConditions: Map<String, String>?
    ): Observable<List<T>> {
        return Observable.fromCallable {
            val realm = Realm.getDefaultInstance()
            var query = realm.where(clazz)

            equalConditions?.let {
                for ((k, v) in it) {
                    query = query.equalTo(k, v)
                }
            }

            val result = realm.copyFromRealm(query.findAll())

            realm.close()

            return@fromCallable result as List<T>
        }
    }

    private fun getLastUpdateLifetime(key: String): Long {
        when (key) {
            LastUpdateDbModel.PROFILE -> return CoreConstants.ONE_DAY_IN_SECOND
            LastUpdateDbModel.PROFILE_LIST -> return CoreConstants.ONE_DAY_IN_SECOND
            else -> {
                throw Exception(CoreConstants.EXCEPTION_NOT_DEFINED_LASTUPDATELIFETIME)
            }
        }
    }

    private fun getLastUpdateKey(key: String, params: String?): String {
        params?.let {
            return "$key $it"
        } ?: run {
            return key
        }
    }
}