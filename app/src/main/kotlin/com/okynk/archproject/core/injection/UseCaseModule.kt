package com.okynk.archproject.core.injection

import com.okynk.archproject.core.usecase.DatabaseUseCase
import com.okynk.archproject.core.usecase.DatabaseUseCaseImpl
import com.okynk.archproject.core.usecase.UseCase
import com.okynk.archproject.core.usecase.UseCaseImpl
import org.koin.dsl.module.module

val useCaseModule = module {
    single<UseCase> { UseCaseImpl(get()) }
    single<DatabaseUseCase> { DatabaseUseCaseImpl(get()) }
}