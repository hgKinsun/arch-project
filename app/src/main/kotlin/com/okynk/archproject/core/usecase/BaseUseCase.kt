package com.okynk.archproject.core.usecase

import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers

abstract class BaseUseCase {

    protected fun <T> composeObservable(task: () -> Observable<T>): Observable<T> {
        return task().subscribeOn(Schedulers.io())
    }

    protected fun composeCompletable(task: () -> Completable): Completable {
        return task().subscribeOn(Schedulers.io())
    }

    protected fun <T> composeSingle(task: () -> Single<T>): Single<T> {
        return task().subscribeOn(Schedulers.io())
    }
}