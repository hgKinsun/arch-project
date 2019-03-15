package com.okynk.archproject.injection

import com.google.gson.Gson
import com.google.gson.JsonParser
import com.okynk.archproject.core.util.SchedulerProvider
import com.okynk.archproject.core.util.SchedulerProviderImpl
import org.koin.dsl.module.module

val utilModule = module {
    single { provideGson() }
    single { provideJsonParser() }
    single<SchedulerProvider> { SchedulerProviderImpl() }
}

private fun provideGson(): Gson {
    return Gson()
}

private fun provideJsonParser(): JsonParser {
    return JsonParser()
}