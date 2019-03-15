/*
 * Copyright (c) 2018 Oky Nugroho Kusumo - Open Source Project
 */

package com.okynk.archproject.core.storage.sharedpreference

import android.content.Context
import com.orhanobut.hawk.Hawk
import io.reactivex.Completable
import io.reactivex.Observable

class SharedPreferenceStorageImpl(context: Context) : SharedPreferenceStorage {

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
    }

    override fun setDummy(str: String): Completable {
        return Completable.fromCallable {
            Hawk.put(PREF_DUMMY, str)
        }
    }

    override fun getDummy(): Observable<String> {
        return Observable.just(Hawk.get(PREF_DUMMY))
    }
}