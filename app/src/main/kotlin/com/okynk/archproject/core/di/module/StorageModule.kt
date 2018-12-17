/*
 * Copyright (c) 2018 Oky Nugroho Kusumo - Open Source Project
 */

package com.okynk.archproject.core.di.module

import android.content.Context
import com.okynk.archproject.core.storage.preference.PreferenceStorage
import com.okynk.archproject.core.storage.preference.PreferenceStorageImpl
import com.okynk.archproject.core.storage.realm.RealmStorage
import com.okynk.archproject.core.storage.realm.RealmStorageImpl
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class StorageModule {
    @Provides
    @Singleton
    fun providePreferenceStorage(context: Context): PreferenceStorage {
        return PreferenceStorageImpl(context)
    }

    @Provides
    @Singleton
    fun provideRealmStorage(): RealmStorage {
        return RealmStorageImpl()
    }
}