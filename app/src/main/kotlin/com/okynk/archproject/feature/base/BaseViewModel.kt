package com.okynk.archproject.feature.base

import androidx.annotation.CallSuper
import androidx.lifecycle.ViewModel
import com.okynk.archproject.core.rx.SingleLiveEvent
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

abstract class BaseViewModel : ViewModel() {

    val messageDialogEvent = SingleLiveEvent<String>()
    val pleaseWaitDialogEvent = SingleLiveEvent<Boolean>()

    private val mDisposable = CompositeDisposable()

    @CallSuper
    override fun onCleared() {
        super.onCleared()
        mDisposable.clear()
    }

    fun execute(task: () -> Disposable) {
        mDisposable.add(task())
    }

    abstract fun start()
}