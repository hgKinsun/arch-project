package com.okynk.archproject.injection

import com.okynk.archproject.core.usecase.database.DatabaseUseCase
import com.okynk.archproject.core.usecase.database.DatabaseUseCaseImpl
import com.okynk.archproject.core.usecase.general.GeneralUseCase
import com.okynk.archproject.core.usecase.general.GeneralUseCaseImpl
import com.okynk.archproject.core.usecase.sharedpreference.SharedPreferenceUseCase
import com.okynk.archproject.core.usecase.sharedpreference.SharedPreferenceUseCaseImpl
import org.koin.dsl.module.module

val useCaseModule = module {
    single<GeneralUseCase> {
        GeneralUseCaseImpl(get(), get())
    }

    single<DatabaseUseCase> {
        DatabaseUseCaseImpl(get(), get())
    }

    single<SharedPreferenceUseCase> {
        SharedPreferenceUseCaseImpl(get(), get())
    }
}