/*
 * Copyright (c) 2019 Oky Nugroho Kusumo - Open Source Project
 */

package com.okynk.archproject.core.datasource.sharedpreference

import io.reactivex.Completable
import io.reactivex.Observable

interface SharedPreferenceDataSource {
    fun setDummy(str: String): Completable
    fun getDummy(): Observable<String>
}