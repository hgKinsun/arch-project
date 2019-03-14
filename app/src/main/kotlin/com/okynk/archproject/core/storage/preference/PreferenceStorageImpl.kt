/*
 * Copyright (c) 2018 Oky Nugroho Kusumo - Open Source Project
 */

package com.okynk.archproject.core.storage.preference

import android.content.Context
import com.orhanobut.hawk.Hawk
import io.reactivex.Completable
import io.reactivex.Observable

class PreferenceStorageImpl(context: Context) : PreferenceStorage {

    companion object {
        private const val PREF_DUMMY = "PREF_DUMMY"
    }

    init {
        Hawk.init(context).build()
    }

    override fun clear(): Completable {
        return Completable.fromCallable {
            Hawk.deleteAll()
        }
//        return Completable.create { emitter ->
//            Hawk.deleteAll()
//            emitter.onComplete()
//        }
    }

    override fun setDummy(str: String): Completable {
        return Completable.fromCallable {
            Hawk.put(PREF_DUMMY, str)
        }
//        return Completable.create { emitter ->
//            Hawk.put(PREF_DUMMY, str)
//            emitter.onComplete()
//        }
    }

    override fun getDummy(): Observable<String> {
        return Observable.just(Hawk.get(PREF_DUMMY))
    }
}