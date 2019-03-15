/*
 * Copyright (c) 2019 Oky Nugroho Kusumo - Open Source Project
 */

package com.okynk.archproject.core.repository.database

import io.reactivex.Completable

interface DatabaseRepository {
    fun clear(): Completable
}