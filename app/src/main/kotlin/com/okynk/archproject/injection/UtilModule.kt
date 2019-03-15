package com.okynk.archproject.injection

import com.google.gson.Gson
import com.google.gson.JsonParser
import org.koin.dsl.module.module

val utilModule = module {
    single { provideGson() }
    single { provideJsonParser() }
}

private fun provideGson(): Gson {
    return Gson()
}

private fun provideJsonParser(): JsonParser {
    return JsonParser()
}