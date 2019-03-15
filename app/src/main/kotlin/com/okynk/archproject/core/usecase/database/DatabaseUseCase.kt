/*
 * Copyright (c) 2019 Oky Nugroho Kusumo - Open Source Project
 */

package com.okynk.archproject.core.usecase.database

import io.reactivex.Completable

interface DatabaseUseCase {
    fun clear(): Completable
}