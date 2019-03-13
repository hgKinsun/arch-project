/*
 * Copyright (c) 2018 Oky Nugroho Kusumo - Open Source Project
 */

package com.okynk.archproject

import android.app.Application
import com.okynk.archproject.core.di.apiModule
import com.okynk.archproject.core.di.dataSourceModule
import com.okynk.archproject.core.di.mapperModule
import com.okynk.archproject.core.di.repositoryModule
import com.okynk.archproject.core.di.storageModule
import com.okynk.archproject.core.di.useCaseModule
import com.okynk.archproject.core.di.utilModule
import com.okynk.archproject.core.di.viewModelModule
import com.okynk.archproject.util.Constants
import io.realm.Realm
import io.realm.RealmConfiguration
import org.koin.android.ext.android.startKoin
import timber.log.Timber

class App : Application() {

    override fun onCreate() {
        super.onCreate()

        if(BuildConfig.DEBUG){
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
                .schemaVersion(Constants.DATABASE_VERSION)
                .deleteRealmIfMigrationNeeded()
                .build()
        )

    }
}