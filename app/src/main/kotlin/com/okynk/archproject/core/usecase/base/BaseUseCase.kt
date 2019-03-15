package com.okynk.archproject.core.usecase.base

import com.okynk.archproject.core.util.SchedulerProvider
import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.Single

abstract class BaseUseCase(private val scheduler: SchedulerProvider) {

    protected fun <T> composeObservable(task: () -> Observable<T>): Observable<T> {
        return task().subscribeOn(scheduler.io()).observeOn(scheduler.ui())
    }

    protected fun composeCompletable(task: () -> Completable): Completable {
        return task().subscribeOn(scheduler.io()).observeOn(scheduler.ui())
    }

    protected fun <T> composeSingle(task: () -> Single<T>): Single<T> {
        return task().subscribeOn(scheduler.io()).observeOn(scheduler.ui())
    }
}