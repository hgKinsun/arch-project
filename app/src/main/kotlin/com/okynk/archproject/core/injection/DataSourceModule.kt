package com.okynk.archproject.core.injection

import com.okynk.archproject.core.datasource.DataSource
import com.okynk.archproject.core.datasource.LocalDataSource
import com.okynk.archproject.core.datasource.RemoteDataSource
import com.okynk.archproject.core.mapper.Mapper
import com.okynk.archproject.core.util.CoreConstants
import org.koin.dsl.module.module

val dataSourceModule = module {

    single<DataSource>(
        name = CoreConstants.DATASOURCE_LOCAL,
        definition = {
            LocalDataSource(
                get(),
                get(),
                get(Mapper.PROFILE_ENTITY_TO_DB),
                get(Mapper.PROFILE_DB_TO_ENTITY)
            )
        }
    )

    single<DataSource>(
        name = CoreConstants.DATASOURCE_REMOTE,
        definition = {
            RemoteDataSource(
                get(),
                get(Mapper.PROFILE_LIST_RESPONSE_TO_ENTITY),
                get(Mapper.PROFILE_RESPONSE_TO_ENTITY)
            )
        }
    )
}