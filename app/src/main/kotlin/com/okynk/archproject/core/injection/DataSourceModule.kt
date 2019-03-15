package com.okynk.archproject.core.injection

import com.okynk.archproject.core.datasource.database.DatabaseDataSource
import com.okynk.archproject.core.datasource.database.DatabaseDataSourceImpl
import com.okynk.archproject.core.datasource.general.GeneralDataSource
import com.okynk.archproject.core.datasource.general.GeneralLocalDataSource
import com.okynk.archproject.core.datasource.general.GeneralRemoteDataSource
import com.okynk.archproject.core.datasource.sharedpreference.SharedPreferenceDataSource
import com.okynk.archproject.core.datasource.sharedpreference.SharedPreferenceDataSourceImpl
import com.okynk.archproject.core.mapper.Mapper
import com.okynk.archproject.core.util.CoreConstants
import org.koin.dsl.module.module

val dataSourceModule = module {

    single<GeneralDataSource>(
        name = CoreConstants.DATASOURCE_LOCAL,
        definition = {
            GeneralLocalDataSource(
                get(),
                get(),
                get(Mapper.PROFILE_ENTITY_TO_DB),
                get(Mapper.PROFILE_DB_TO_ENTITY)
            )
        }
    )

    single<GeneralDataSource>(
        name = CoreConstants.DATASOURCE_REMOTE,
        definition = {
            GeneralRemoteDataSource(
                get(),
                get(Mapper.PROFILE_LIST_RESPONSE_TO_ENTITY),
                get(Mapper.PROFILE_RESPONSE_TO_ENTITY)
            )
        }
    )

    single<DatabaseDataSource> {
        DatabaseDataSourceImpl(get())
    }

    single<SharedPreferenceDataSource> {
        SharedPreferenceDataSourceImpl(get())
    }
}