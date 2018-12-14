/*
 * Copyright (c) 2018 Oky Nugroho Kusumo - Open Source Project
 */

package com.okynk.archproject.core.storage.realm

import com.okynk.archproject.core.storage.model.LastUpdateDbModel
import com.okynk.archproject.core.util.getCurrentTimestamp
import io.reactivex.Completable
import io.realm.Realm

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

    override fun isLastUpdateExpired(key: String): Boolean {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun invalidateLastUpdate(key: String): Completable {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun clear(): Completable {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}