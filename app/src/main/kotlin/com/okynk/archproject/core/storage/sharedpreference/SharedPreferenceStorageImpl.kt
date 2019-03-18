/*
 * Copyright (c) 2018 Oky Nugroho Kusumo - Open Source Project
 */

package com.okynk.archproject.core.storage.sharedpreference

import com.okynk.archproject.core.wrapper.hawk.HawkWrapper
import io.reactivex.Completable
import io.reactivex.Observable

class SharedPreferenceStorageImpl(private val mHawkWrapper: HawkWrapper) : SharedPreferenceStorage {

    companion object {
        const val PREF_DUMMY = "PREF_DUMMY"
    }

    init {
        mHawkWrapper.initialize()
    }

    override fun clear(): Completable {
        return Completable.fromCallable {
            mHawkWrapper.deleteAll()
        }
    }

    override fun setDummy(str: String): Completable {
        return Completable.fromCallable {
            mHawkWrapper.put(PREF_DUMMY, str)
        }
    }

    override fun getDummy(): Observable<String> {
        return Observable.just(mHawkWrapper.get(PREF_DUMMY))
    }
}