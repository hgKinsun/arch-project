package com.okynk.archproject.core.injection

import com.okynk.archproject.core.usecase.database.DatabaseUseCase
import com.okynk.archproject.core.usecase.database.DatabaseUseCaseImpl
import com.okynk.archproject.core.usecase.general.GeneralUseCase
import com.okynk.archproject.core.usecase.general.GeneralUseCaseImpl
import org.koin.dsl.module.module

val useCaseModule = module {
    single<GeneralUseCase> {
        GeneralUseCaseImpl(
            get()
        )
    }
    single<DatabaseUseCase> {
        DatabaseUseCaseImpl(
            get()
        )
    }
}