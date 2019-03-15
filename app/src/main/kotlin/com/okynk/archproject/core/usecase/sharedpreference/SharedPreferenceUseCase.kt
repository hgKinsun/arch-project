/*
 * Copyright (c) 2019 Oky Nugroho Kusumo - Open Source Project
 */

package com.okynk.archproject.core.usecase.sharedpreference

import io.reactivex.Completable
import io.reactivex.Observable

interface SharedPreferenceUseCase {
    fun setDummy(str: String): Completable
    fun getDummy(): Observable<String>
}