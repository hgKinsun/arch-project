package com.okynk.archproject.core.injection

import com.okynk.archproject.core.repository.database.DatabaseRepository
import com.okynk.archproject.core.repository.database.DatabaseRepositoryImpl
import com.okynk.archproject.core.repository.general.GeneralRepository
import com.okynk.archproject.core.repository.general.GeneralRepositoryImpl
import com.okynk.archproject.core.util.CoreConstants
import org.koin.dsl.module.module

val repositoryModule = module {
    single<GeneralRepository> {
        GeneralRepositoryImpl(
            get(CoreConstants.DATASOURCE_LOCAL),
            get(CoreConstants.DATASOURCE_REMOTE)
        )
    }

    single<DatabaseRepository> {
        DatabaseRepositoryImpl(
            get()
        )
    }
}