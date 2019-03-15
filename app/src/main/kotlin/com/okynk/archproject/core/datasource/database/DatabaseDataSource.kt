/*
 * Copyright (c) 2019 Oky Nugroho Kusumo - Open Source Project
 */

package com.okynk.archproject.core.datasource.database

import io.reactivex.Completable

interface DatabaseDataSource {
    fun clear(): Completable
}