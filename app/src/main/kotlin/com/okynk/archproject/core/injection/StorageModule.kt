package com.okynk.archproject.core.injection

import com.okynk.archproject.core.storage.preference.PreferenceStorage
import com.okynk.archproject.core.storage.preference.PreferenceStorageImpl
import com.okynk.archproject.core.storage.realm.RealmStorage
import com.okynk.archproject.core.storage.realm.RealmStorageImpl
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module.module

val storageModule = module {
    single<PreferenceStorage> { PreferenceStorageImpl(androidApplication()) }
    single<RealmStorage> { RealmStorageImpl() }
}
