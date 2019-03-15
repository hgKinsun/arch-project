package com.okynk.archproject.core.injection

import com.okynk.archproject.core.storage.realm.RealmStorage
import com.okynk.archproject.core.storage.realm.RealmStorageImpl
import com.okynk.archproject.core.storage.sharedpreference.SharedPreferenceStorage
import com.okynk.archproject.core.storage.sharedpreference.SharedPreferenceStorageImpl
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module.module

val storageModule = module {
    single<SharedPreferenceStorage> { SharedPreferenceStorageImpl(androidApplication()) }
    single<RealmStorage> { RealmStorageImpl() }
}
