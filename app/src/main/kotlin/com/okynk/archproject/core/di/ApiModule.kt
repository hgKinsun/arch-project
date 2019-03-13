package com.okynk.archproject.core.di

import com.okynk.archproject.BuildConfig
import com.okynk.archproject.core.api.ApiService
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module.module
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

val apiModule = module {

    single { createOkHttpClient() }
    single { createRetrofit<ApiService>(get()) }

}

private inline fun <reified T> createRetrofit(okHttpClient: OkHttpClient): T {
    val retrofit = Retrofit.Builder()
        .baseUrl("https://randomapi.com/")
        .client(okHttpClient)
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .build()
    return retrofit.create(T::class.java)
}

private fun createOkHttpClient(): OkHttpClient {
    val okHttpClientBuilder: OkHttpClient.Builder = OkHttpClient.Builder()
        .connectTimeout(60L, TimeUnit.SECONDS)
        .readTimeout(60L, TimeUnit.SECONDS)
    if (BuildConfig.DEBUG) {
        val loggingInterceptor =
            HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
        okHttpClientBuilder.addInterceptor(loggingInterceptor)
    }
    return okHttpClientBuilder.build()
}