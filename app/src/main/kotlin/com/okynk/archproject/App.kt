/*
 * Copyright (c) 2018 Oky Nugroho Kusumo - Open Source Project
 */

package com.okynk.archproject

import android.app.Application
import com.okynk.archproject.core.injection.apiModule
import com.okynk.archproject.core.injection.dataSourceModule
import com.okynk.archproject.core.injection.mapperModule
import com.okynk.archproject.core.injection.repositoryModule
import com.okynk.archproject.core.injection.storageModule
import com.okynk.archproject.core.injection.useCaseModule
import com.okynk.archproject.core.injection.utilModule
import com.okynk.archproject.core.injection.viewModelModule
import com.okynk.archproject.core.util.CoreConstants
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