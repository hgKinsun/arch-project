/*
 * Copyright (c) 2018 Oky Nugroho Kusumo - Open Source Project
 */

package com.okynk.archproject.core.storage.sharedpreference

import io.reactivex.Completable
import io.reactivex.Observable

interface SharedPreferenceStorage {
    fun clear(): Completable
    fun setDummy(str: String): Completable
    fun getDummy(): Observable<String>
}