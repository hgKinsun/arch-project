/*
 * Copyright (c) 2019 Oky Nugroho Kusumo - Open Source Project
 */

package com.okynk.archproject.core.usecase.database

import com.okynk.archproject.core.repository.database.DatabaseRepository
import com.okynk.archproject.core.usecase.base.BaseUseCase
import com.okynk.archproject.core.util.SchedulerProvider
import io.reactivex.Completable

class DatabaseUseCaseImpl(
    private val repository: DatabaseRepository,
    scheduler: SchedulerProvider
) : BaseUseCase(scheduler),
    DatabaseUseCase {
    override fun clear(): Completable {
        return composeCompletable { repository.clear() }
    }
}