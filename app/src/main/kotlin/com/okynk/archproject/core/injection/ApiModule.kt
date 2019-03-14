package com.okynk.archproject.core.injection

import com.google.gson.Gson
import com.okynk.archproject.BuildConfig
import com.okynk.archproject.core.api.ApiService
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module.module
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

private const val HTTP_LOGGING_INTERCEPTOR = "HTTP_LOGGING_INTERCEPTOR"
private const val CONNECTION_TIMEOUT = 60L

val apiModule = module {

    single { provideOkHttpClient(get()) }
    single { provideRetrofit<ApiService>(get(), get()) }
    single(
        name = HTTP_LOGGING_INTERCEPTOR,
        definition = { provideHttpLoggingInterceptor() }
    )
}

private inline fun <reified T> provideRetrofit(okHttpClient: OkHttpClient, gson: Gson): T {
    val retrofit = Retrofit.Builder()
        .baseUrl("https://randomapi.com/")
        .client(okHttpClient)
        .addConverterFactory(GsonConverterFactory.create(gson))
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .build()
    return retrofit.create(T::class.java)
}

private fun provideOkHttpClient(httpLoggingInterceptor: Interceptor): OkHttpClient {
    return OkHttpClient.Builder()
        .connectTimeout(CONNECTION_TIMEOUT, TimeUnit.SECONDS)
        .readTimeout(CONNECTION_TIMEOUT, TimeUnit.SECONDS)
        .retryOnConnectionFailure(true)
        .addInterceptor(httpLoggingInterceptor)
        .build()
}

private fun provideHttpLoggingInterceptor(): Interceptor {
    val interceptor = HttpLoggingInterceptor()
    interceptor.level = if (BuildConfig.DEBUG) {
        HttpLoggingInterceptor.Level.BODY
    } else {
        HttpLoggingInterceptor.Level.NONE
    }
    return interceptor
}