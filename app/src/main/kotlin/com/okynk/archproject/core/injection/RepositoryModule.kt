package com.okynk.archproject.core.injection

import com.okynk.archproject.core.repository.Repository
import com.okynk.archproject.core.repository.RepositoryImpl
import com.okynk.archproject.core.util.CoreConstants
import org.koin.dsl.module.module

val repositoryModule = module {
    single<Repository> {
        RepositoryImpl(get(CoreConstants.DATASOURCE_LOCAL), get(CoreConstants.DATASOURCE_REMOTE))
    }
}