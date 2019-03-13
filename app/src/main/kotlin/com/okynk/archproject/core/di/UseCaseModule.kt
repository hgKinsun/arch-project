package com.okynk.archproject.core.di

import com.okynk.archproject.core.usecase.UseCase
import com.okynk.archproject.core.usecase.UseCaseImpl
import org.koin.dsl.module.module

val useCaseModule = module {
    single<UseCase> { UseCaseImpl(get()) }
}