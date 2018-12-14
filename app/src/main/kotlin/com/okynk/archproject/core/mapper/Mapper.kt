/*
 * Copyright (c) 2018 Oky Nugroho Kusumo - Open Source Project
 */

package com.okynk.archproject.core.mapper

import io.reactivex.Observable

abstract class Mapper<in FROM, TO> {
    abstract fun mapFrom(from: FROM): TO

    fun observable(from: FROM): Observable<TO> {
        return Observable.fromCallable { mapFrom(from) }
    }

    fun observable(from: List<FROM>): Observable<List<TO>> {
        return Observable.fromCallable { from.map { mapFrom(it) } }
    }
}