package com.okynk.archproject.core.di

import com.okynk.archproject.core.repository.Repository
import com.okynk.archproject.core.repository.RepositoryImpl
import com.okynk.archproject.util.Constants
import org.koin.dsl.module.module

val repositoryModule = module {
    single<Repository> {
        RepositoryImpl(get(Constants.DATASOURCE_LOCAL), get(Constants.DATASOURCE_REMOTE))
    }
}