package com.okynk.archproject.injection

import com.okynk.archproject.core.storage.realm.RealmStorage
import com.okynk.archproject.core.storage.realm.RealmStorageImpl
import com.okynk.archproject.core.storage.sharedpreference.SharedPreferenceStorage
import com.okynk.archproject.core.storage.sharedpreference.SharedPreferenceStorageImpl
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module.module

val storageModule = module {
    single<SharedPreferenceStorage> { SharedPreferenceStorageImpl(androidContext()) }
    single<RealmStorage> { RealmStorageImpl() }
}
