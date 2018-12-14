/*
 * Copyright (c) 2018 Oky Nugroho Kusumo - Open Source Project
 */

package com.okynk.archproject.core.storage.preference

import io.reactivex.Completable
import io.reactivex.Observable

interface PreferenceStorage {
    fun clear(): Completable
    fun setDummy(str: String): Completable
    fun getDummy(): Observable<String>
}