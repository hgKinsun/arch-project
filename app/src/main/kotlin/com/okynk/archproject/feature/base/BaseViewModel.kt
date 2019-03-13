package com.okynk.archproject.feature.base

import androidx.annotation.CallSuper
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

abstract class BaseViewModel<T> : ViewModel() {
    val viewState: MutableLiveData<T> = MutableLiveData()

    val disposable = CompositeDisposable()

    fun execute(task: () -> Disposable) {
        disposable.add(task())
    }

    @CallSuper
    override fun onCleared() {
        super.onCleared()
        disposable.clear()
    }
}