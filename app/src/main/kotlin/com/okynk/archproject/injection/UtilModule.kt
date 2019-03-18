package com.okynk.archproject.injection

import com.google.gson.Gson
import com.google.gson.JsonParser
import com.okynk.archproject.core.util.generalutil.GeneralUtil
import com.okynk.archproject.core.util.generalutil.GeneralUtilImpl
import com.okynk.archproject.core.wrapper.hawk.HawkWrapper
import com.okynk.archproject.core.wrapper.hawk.HawkWrapperImpl
import com.okynk.archproject.core.wrapper.realm.RealmWrapper
import com.okynk.archproject.core.wrapper.realm.RealmWrapperImpl
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module.module

val utilModule = module {
    single { provideGson() }
    single { provideJsonParser() }
    single<HawkWrapper> { HawkWrapperImpl(androidContext()) }
    single<RealmWrapper> { RealmWrapperImpl() }
    single<GeneralUtil> { GeneralUtilImpl() }
}

private fun provideGson(): Gson {
    return Gson()
}

private fun provideJsonParser(): JsonParser {
    return JsonParser()
}