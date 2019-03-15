/*
 * Copyright (c) 2019 Oky Nugroho Kusumo - Open Source Project
 */

package com.okynk.archproject.core.datasource.sharedpreference

import com.okynk.archproject.core.storage.sharedpreference.SharedPreferenceStorage
import io.reactivex.Completable
import io.reactivex.Observable

class SharedPreferenceDataSourceImpl(private val storage: SharedPreferenceStorage) :
    SharedPreferenceDataSource {
    override fun setDummy(str: String): Completable {
        return storage.setDummy(str)
    }

    override fun getDummy(): Observable<String> {
        return storage.getDummy()
    }

}