package com.okynk.archproject.core.usecase

import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

abstract class BaseUseCase {

    protected fun <T> composeObservable(task: () -> Observable<T>): Observable<T> {
        return task().subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
    }

    protected fun composeCompletable(task: () -> Completable): Completable {
        return task().subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
    }

    protected fun <T> composeSingle(task: () -> Single<T>): Single<T> {
        return task().subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
    }
}