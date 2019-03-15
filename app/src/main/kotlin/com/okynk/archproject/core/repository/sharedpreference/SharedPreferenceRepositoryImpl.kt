/*
 * Copyright (c) 2019 Oky Nugroho Kusumo - Open Source Project
 */

package com.okynk.archproject.core.repository.sharedpreference

import com.okynk.archproject.core.datasource.sharedpreference.SharedPreferenceDataSource
import io.reactivex.Completable
import io.reactivex.Observable

class SharedPreferenceRepositoryImpl(private val dataSource: SharedPreferenceDataSource) :
    SharedPreferenceRepository {
    override fun setDummy(str: String): Completable {
        return dataSource.setDummy(str)
    }

    override fun getDummy(): Observable<String> {
        return dataSource.getDummy()
    }

}