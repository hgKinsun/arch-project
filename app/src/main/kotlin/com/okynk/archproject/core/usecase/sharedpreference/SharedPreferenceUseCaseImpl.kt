/*
 * Copyright (c) 2019 Oky Nugroho Kusumo - Open Source Project
 */

package com.okynk.archproject.core.usecase.sharedpreference

import com.okynk.archproject.core.repository.sharedpreference.SharedPreferenceRepository
import com.okynk.archproject.core.usecase.base.BaseUseCase
import com.okynk.archproject.core.util.SchedulerProvider
import io.reactivex.Completable
import io.reactivex.Observable

class SharedPreferenceUseCaseImpl(
    private val repository: SharedPreferenceRepository,
    scheduler: SchedulerProvider
) :
    BaseUseCase(scheduler), SharedPreferenceUseCase {
    override fun setDummy(str: String): Completable {
        return composeCompletable { repository.setDummy(str) }
    }

    override fun getDummy(): Observable<String> {
        return composeObservable { repository.getDummy() }
    }
}