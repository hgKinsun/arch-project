/*
 * Copyright (c) 2018 Oky Nugroho Kusumo - Open Source Project
 */

package com.okynk.archproject

import android.app.Application
import com.okynk.archproject.core.util.CoreConstants
import com.okynk.archproject.injection.apiModule
import com.okynk.archproject.injection.dataSourceModule
import com.okynk.archproject.injection.mapperModule
import com.okynk.archproject.injection.repositoryModule
import com.okynk.archproject.injection.storageModule
import com.okynk.archproject.injection.useCaseModule
import com.okynk.archproject.injection.utilModule
import com.okynk.archproject.injection.viewModelModule
import io.realm.Realm
import io.realm.RealmConfiguration
import org.koin.android.ext.android.startKoin
import timber.log.Timber

class App : Application() {

    override fun onCreate() {
        super.onCreate()

        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }

        startKoin(
            this,
            listOf(
                apiModule,
                dataSourceModule,
                mapperModule,
                repositoryModule,
                storageModule,
                useCaseModule,
                utilModule,
                viewModelModule
            )
        )

        Realm.init(this)
        Realm.setDefaultConfiguration(
            RealmConfiguration.Builder()
                .schemaVersion(CoreConstants.DATABASE_VERSION)
                .deleteRealmIfMigrationNeeded()
                .build()
        )
    }
}