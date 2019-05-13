package com.okynk.archproject.rx

import io.reactivex.Observable
import io.reactivex.schedulers.Schedulers
import org.junit.Test

class TestRxError {
    // This one outputs "subscribe.onError"
    @Test
    @Throws(InterruptedException::class)
    fun observable_doOnError_subscribingToError() {
        val obs = getErrorProducingObservable()
            .doOnError({ throwable -> println("doOnError") })

        obs.subscribeOn(Schedulers.io()).observeOn(Schedulers.io()).subscribe(
            { s -> },
            { error -> println("subscribe.onError") }
        )
        Thread.sleep(300)
    }

    // This one outputs "subscribe.onError"
    @Test
    @Throws(InterruptedException::class)
    fun observable_onErrorReturn() {
        val obs = getErrorProducingObservable()
            .onErrorReturn({ throwable -> "Yeah I got this" })

        obs.subscribeOn(Schedulers.io()).observeOn(Schedulers.io()).subscribe(
            { s -> println("got: $s") },
            { error -> println("subscribe.onError") }
        )
        Thread.sleep(300)
    }

    private fun getErrorProducingObservable(): Observable<String> {
        return Observable.create({ subscriber -> subscriber.onError(RuntimeException("Somebody set up us the bomb")) })
    }
}