package com.okynk.archproject.feature.base

import androidx.annotation.CallSuper
import androidx.lifecycle.ViewModel
import com.okynk.archproject.util.SingleLiveEvent
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

abstract class BaseViewModel : ViewModel() {

    val errorMessageLiveData = SingleLiveEvent<String>()
    val pleaseWaitLiveData = SingleLiveEvent<Boolean>()
    val disposable = CompositeDisposable()

    fun execute(task: () -> Disposable) {
        disposable.add(task())
    }

    @CallSuper
    override fun onCleared() {
        super.onCleared()
        disposable.clear()
    }

    abstract fun start()
}