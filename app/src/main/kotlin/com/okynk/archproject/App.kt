/*
 * Copyright (c) 2018 Oky Nugroho Kusumo - Open Source Project
 */

package com.okynk.archproject

import android.app.Application
import com.okynk.archproject.core.di.component.ApplicationComponent
import com.okynk.archproject.core.di.component.DaggerApplicationComponent
import com.okynk.archproject.core.di.module.*
import com.okynk.archproject.util.Constants
import io.realm.Realm
import io.realm.RealmConfiguration
import timber.log.Timber

class App : Application() {
    lateinit var applicationComponent: ApplicationComponent

    override fun onCreate() {
        super.onCreate()

        if(BuildConfig.DEBUG){
            Timber.plant(Timber.DebugTree())
        }

        Realm.init(this)
        Realm.setDefaultConfiguration(
            RealmConfiguration.Builder()
                .schemaVersion(Constants.DATABASE_VERSION)
                .deleteRealmIfMigrationNeeded()
                .build()
        )
        applicationComponent = DaggerApplicationComponent.builder()
            .applicationModule(ApplicationModule(this))
            .mapperModule(MapperModule())
            .storageModule(StorageModule())
            .apiModule(ApiModule())
            .dataSourceModule(DataSourceModule())
            .repositoryModule(RepositoryModule())
            .useCaseModule(UseCaseModule())
            .build()

    }
}