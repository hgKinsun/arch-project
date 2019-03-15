/*
 * Copyright (c) 2019 Oky Nugroho Kusumo - Open Source Project
 */

package com.okynk.archproject.core.datasource

import io.reactivex.Completable

interface DatabaseDataSource {
    fun clear(): Completable
}