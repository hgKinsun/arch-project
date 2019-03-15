/*
 * Copyright (c) 2019 Oky Nugroho Kusumo - Open Source Project
 */

package com.okynk.archproject.core.datasource.sharedpreference

import io.reactivex.Completable
import io.reactivex.Observable

class SharedPreferenceDataSourceImpl(private val dataSource: SharedPreferenceDataSource) :
    SharedPreferenceDataSource {
    override fun setDummy(str: String): Completable {
        return dataSource.setDummy(str)
    }

    override fun getDummy(): Observable<String> {
        return dataSource.getDummy()
    }

}