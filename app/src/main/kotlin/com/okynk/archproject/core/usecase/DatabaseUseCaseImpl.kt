/*
 * Copyright (c) 2019 Oky Nugroho Kusumo - Open Source Project
 */

package com.okynk.archproject.core.usecase

import com.okynk.archproject.core.repository.DatabaseRepository
import io.reactivex.Completable

class DatabaseUseCaseImpl(private val repository: DatabaseRepository) : BaseUseCase(),
    DatabaseUseCase {
    override fun clear(): Completable {
        return composeCompletable { repository.clear() }
    }
}