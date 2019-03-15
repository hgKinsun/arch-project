/*
 * Copyright (c) 2019 Oky Nugroho Kusumo - Open Source Project
 */

package com.okynk.archproject.core.datasource.database

import com.okynk.archproject.core.storage.realm.RealmStorage
import io.reactivex.Completable

class DatabaseDataSourceImpl(private val realmStorage: RealmStorage) :
    DatabaseDataSource {
    override fun clear(): Completable {
        return realmStorage.clear()
    }
}