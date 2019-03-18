/*
 * Copyright (c) 2018 Oky Nugroho Kusumo - Open Source Project
 */

package com.okynk.archproject.core.storage.realm

import com.okynk.archproject.core.storage.model.LastUpdateDbModel
import com.okynk.archproject.core.util.CoreConstants
import com.okynk.archproject.core.util.generalutil.GeneralUtil
import com.okynk.archproject.core.wrapper.realm.RealmWrapper
import io.reactivex.Completable
import io.reactivex.Observable
import io.realm.RealmObject

class RealmStorageImpl(
    private val mRealmWrapper: RealmWrapper,
    private val mGeneralUtil: GeneralUtil
) : RealmStorage {
    override fun touchLastUpdate(key: String, params: String?): Completable {
        return Completable.fromCallable {
            val lastUpdate =
                LastUpdateDbModel(getLastUpdateKey(key, params), mGeneralUtil.getCurrentTimestamp())
            mRealmWrapper.insertOrUpdate(lastUpdate)
        }
    }

    override fun isLastUpdateExpired(key: String, params: String?): Observable<Boolean> {
        return Observable.fromCallable {
            val conditions = HashMap<String, String>()
            conditions[LastUpdateDbModel.QUERY_KEY] = getLastUpdateKey(key, params)
            val lastUpdate =
                mRealmWrapper.getFirst<LastUpdateDbModel>(LastUpdateDbModel::class.java, conditions)
            var expired = true
            lastUpdate?.let {
                expired =
                    (mGeneralUtil.getCurrentTimestamp() - it.timestamp > getLastUpdateLifetime(key))
            }

            return@fromCallable expired
        }
    }

    override fun clear(): Completable {
        return Completable.fromCallable {
            mRealmWrapper.deleteAll()
        }
    }

    override fun <T : RealmObject> insertOrUpdate(data: T): Completable {
        return Completable.fromCallable {
            mRealmWrapper.insertOrUpdate(data)
        }
    }

    override fun <T : RealmObject> insert(data: T): Completable {
        return Completable.fromCallable {
            mRealmWrapper.insert(data)
        }
    }

    override fun <T : RealmObject> getFirst(
        clazz: Class<out RealmObject>,
        equalConditions: Map<String, String>?
    ): Observable<T> {
        return Observable.fromCallable {
            return@fromCallable mRealmWrapper.getFirst<T>(clazz, equalConditions)
        }
    }

    override fun <T : RealmObject> getAll(
        clazz: Class<out RealmObject>,
        equalConditions: Map<String, String>?
    ): Observable<List<T>> {
        return Observable.fromCallable {
            return@fromCallable mRealmWrapper.getAll<T>(clazz, equalConditions)
        }
    }

    private fun getLastUpdateLifetime(key: String): Long {
        return when (key) {
            LastUpdateDbModel.PROFILE -> CoreConstants.ONE_DAY_IN_SECOND
            LastUpdateDbModel.PROFILE_LIST -> CoreConstants.ONE_DAY_IN_SECOND
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